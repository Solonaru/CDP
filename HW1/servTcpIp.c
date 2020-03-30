#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <errno.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>

#define PORT 2013
#define MAX_THREADS_COUNT 20

extern int errno;

void *thread_func(void *client_socket_ptr);

int main()
{
  struct sockaddr_in server;
  struct sockaddr_in from;
  int sd;
  pthread_t tids[MAX_THREADS_COUNT];
  int i = 0;

  /* create socket */
  if (-1 == (sd = socket(AF_INET, SOCK_STREAM, 0)))
  {
    perror("[server] Couldn't create socket!\n");
    return errno;
  }

  bzero(&server, sizeof(server));
  bzero(&from, sizeof(from));

  /* configure server structure */
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = htonl(INADDR_ANY);
  server.sin_port = htons(PORT);

  /* assign address to socket */
  if (-1 == bind(sd, (struct sockaddr *)&server, sizeof(struct sockaddr)))
  {
    perror("[server] Couldn't perform bind.\n");
    return errno;
  }

  /* listen on socket descriptor */
  if (-1 == listen(sd, MAX_THREADS_COUNT))
  {
    perror("[server] Couldn't listen for incoming connections.\n");
    return errno;
  }

  printf("[server] Connection port %d...\n", PORT);
  fflush(stdout);

  while (1)
  {
    int client_socket;
    int length = sizeof(from);
    pthread_t thread_id;

    /* wait for incoming connections */
    client_socket = accept(sd, (struct sockaddr *)&from, &length);

    if (0 > client_socket)
    {
      perror("[server] Couldn't perform accept.\n");
    }
    else
    {
      if (0 != pthread_detach(&tids[i], NULL, thread_func, &client_socket))
      {
        perror("[server] Failed to create thread!\n");
      }
      else
      {
        pthread_join(tids[i], NULL);

        if (MAX_THREADS_COUNT - 1 <= i)
        {
          i = 0;
        }
        else
        {
          i++;
        }
      }
    }
  }
}

void *thread_func(void *client_socket_ptr)
{
  int client_socket = *((int *)client_socket_ptr);
  char msg_in[100];
  char msg_out[100];

  /* upon connection wait for the message */
  bzero(msg_in, 100);
  printf("[server] Waiting for the message...\n");
  fflush(stdout);

  /* read the message */
  if (0 >= read(client_socket, msg_in, 100))
  {
    perror("[server] Couldn't perform read.\n");
    close(client_socket);
  }
  else
  {
    printf("[server] Message successfully received...%s\n", msg_in);

    bzero(msg_out, 100);
    strcat(msg_out, "Hello ");
    strcat(msg_out, msg_in);

    printf("[server] Sending back response...%s\n", msg_out);

    /* write response */
    if (0 >= write(client_socket, msg_out, 100))
    {
      perror("[server] Couldn't perform write.\n");
    }
    else
    {
      printf("[server] Response successfully sent.\n");
    }

    close(client_socket);
  }

  pthread_exit(NULL);
}
