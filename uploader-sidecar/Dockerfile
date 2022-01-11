FROM python:3.8.12-slim

WORKDIR /app
ADD requirements.txt .
RUN python3 -m pip install -r requirements.txt --ignore-installed six
VOLUME ["/dumps"]
ADD uploader.py .
ENTRYPOINT ["python3", "uploader.py"]