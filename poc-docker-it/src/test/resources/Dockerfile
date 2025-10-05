FROM docker-all.repo.sonatype.com/python:3.13-alpine

RUN mkdir -p /home/app

COPY api.py /home/app/api.py

CMD cd /home/app && pip install Flask && python api.py
