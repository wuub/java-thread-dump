import boto3
import typer
import os
import os.path
import time
import structlog

log = structlog.get_logger()
s3 = boto3.client('s3')

def main(directory: str, dst_bucket: str, dst_prefix: str, dry: bool=False):
    typer.echo(f"Setup {directory} -> {dst_bucket}/{dst_prefix}")
    while True:
        log.info(f"scanning {directory}")
        for root, _, files in os.walk(directory):
            for name in files:
                fname = os.path.join(root, name)
                log.info(f"processing {fname}")
                with open(fname, "rb") as f:
                    # this accepts standard creds locations, so it works
                    # with docker run .. -e AWS_ACCESS_KEY_ID or IRSA
                    log.info(f"uploading {fname} --> s3://{dst_bucket}/{dst_prefix}{name}")
                    if not dry:
                        s3.upload_fileobj(f, dst_bucket, dst_prefix + name)
                log.info(f"removing {fname}")
                os.unlink(fname)
        time.sleep(10)

if __name__ == "__main__":
    typer.run(main)