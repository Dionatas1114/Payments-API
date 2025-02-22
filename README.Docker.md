### Building and running your application

DEV
- UP - `docker-compose --env-file env.dev -f docker-compose.dev.yaml up --build -d`
- Down - `docker-compose --env-file env.dev -f docker-compose.dev.yaml down`
- See env vars - `cat env.dev`

PROD
- UP - `docker-compose --env-file env.prod -f docker-compose.prod.yaml up --build -d`
- Down - `docker-compose --env-file env.prod -f docker-compose.prod.yaml down`
- See env vars - `cat env.prod`
