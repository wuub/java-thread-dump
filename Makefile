.PHONY=image

image:
	docker build -t java-hello-world .
	docker build -t uploader-sidecar ./uploader-sidecar

