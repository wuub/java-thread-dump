Use local kubernetes, preferably docker-desktop one

```
$ kubectl config use-context docker-desktop
```

Running
```
$ make
$ kubectl apply -f hello-world.yaml
```

See how thread dumps are "uploaded"

```
$ kubectl logs hello-world uploader-sidecar
Setup /dumps -> TEST-BUCKET/dumps-prefix/
2022-01-11 19:38.53 [info     ] scanning /dumps
2022-01-11 19:39.03 [info     ] scanning /dumps
2022-01-11 19:39.03 [info     ] processing /dumps/thread-dump-2022-01-11T19:39:01+0000.thread
2022-01-11 19:39.03 [info     ] uploading /dumps/thread-dump-2022-01-11T19:39:01+0000.thread --> s3://TEST-BUCKET/dumps-prefix/thread-dump-2022-01-11T19:39:01+0000.thread
2022-01-11 19:39.03 [info     ] removing /dumps/thread-dump-2022-01-11T19:39:01+0000.thread
2022-01-11 19:39.13 [info     ] scanning /dumps
```

To trigger a Heap Dump event:

```
$ kubectl port-forward pod/hello-world 8080:8080 &
$ curl localhost:8080
Handling connection for 8080
Hello world%
$ curl localhost:8080
Handling connection for 8080
Hello world%
$ curl localhost:8080
Handling connection for 8080
Hello world%
$ curl localhost:8080
Handling connection for 8080
curl: (52) Empty reply from server
```

this will create a heapdump in `/dumps` and it will be picked up by the sidecar

```
2022-01-11 19:43.43 [info     ] scanning /dumps
2022-01-11 19:43.43 [info     ] processing /dumps/thread-dump-2022-01-11T19:43:39+0000.thread
2022-01-11 19:43.43 [info     ] uploading /dumps/thread-dump-2022-01-11T19:43:39+0000.thread --> s3://TEST-BUCKET/dumps-prefix/thread-dump-2022-01-11T19:43:39+0000.thread
2022-01-11 19:43.43 [info     ] removing /dumps/thread-dump-2022-01-11T19:43:39+0000.thread
2022-01-11 19:43.43 [info     ] processing /dumps/java_pid115.hprof
2022-01-11 19:43.43 [info     ] uploading /dumps/java_pid115.hprof --> s3://TEST-BUCKET/dumps-prefix/java_pid115.hprof
2022-01-11 19:43.43 [info     ] removing /dumps/java_pid115.hprof
2022-01-11 19:43.53 [info     ] scanning /dumps
```


