#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <errno.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <netdb.h>
#include <string.h>

extern int errno;

int main(int argc, char *argv[])
{
  int sd;
  struct sockaddr_in server;
  char msg_out[100];
  int conn_port;

  if (argc != 3)
  {
    printf("Pattern: %s <adresa_server> <conn_port>\n", argv[0]);
    return -1;
  }

  conn_port = atoi(argv[2]);

  if (-1 == (sd = socket(AF_INET, SOCK_STREAM, 0)))
  {
    perror("[client] Error creating socket.\n");
    return errno;
  }

  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr(argv[1]);
  server.sin_port = htons(conn_port);

  if (-1 == connect(sd, (struct sockaddr *)&server, sizeof(struct sockaddr)))
  {
    perror("[client] Couldn't perform connection.\n");
    return errno;
  }

  bzero(msg_out, 100);
  printf("[client] Insert message: ");
  fflush(stdout);
  read(0, msg_out, 100);

  if (0 >= write(sd, msg_out, 100))
  {
    perror("[client] Couldn't perform write.\n");
    return errno;
  }

  if (0 > read(sd, msg_out, 100))
  {
    perror("[client] Couldn't perform read.\n");
    return errno;
  } else {
	printf("[client] Received message: %s\n", msg_out);
	
	bzero(msg_out, 100);
	printf("[client] Insert response: ");
	fflush(stdout);
	read(0, msg_out, 100);

	if (0 >= write(sd, msg_out, 100))
	{
		perror("[client] Couldn't perform write.\n");
		return errno;
	}

	if (0 > read(sd, msg_out, 100))
	{
		perror("[client] Couldn't perform read.\n");
		return errno;
	}

	printf("[client] Received message: %s\n", msg_out);
  }

  close(sd);
}
