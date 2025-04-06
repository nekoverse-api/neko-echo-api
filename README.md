# Neko Echo Api

Neko Echo Api is a simple web service that receives HTTP requests and responds by returning the same data sent by the client.
It supports http methods like GET, POST, PUT, DELETE, PATCH, and OPTIONS, making it ideal for testing and debugging api tools.

## Local Development

**Install Dependencies Java 21 with sdkman**

```sh
curl -s "https://get.sdkman.io" | bash
sdk install java 21.0.6-amzn
```

**Build**

```sh
./gradlew build 
```

**Run in watch mode**

```sh
./gradlew run -t 
```

**Use**
```sh
curl "http://localhost:3666/api/v1/test?limit=100&skip=50&q=test" | jq
```

## Build & Publish Docker Image

**Setup Credentials**

```sh
read -s DOCKERHUB_USERNAME
read -s DOCKERHUB_TOKEN
```

**Build Docker Image**

```sh
./gradlew dockerBuildNative
```

**Push Docker Image**

```sh
./gradlew dockerPushNative
```

**Very Important Clean Credentials**

```she
export DOCKERHUB_USERNAME=""
export DOCKERHUB_TOKEN=""
```

## Production Mode

**Run as a Container**
```sh
docker run --detach --rm -p 3666:3666 \
  --env NEKO_ECHO_API_BASE_URL='https://echo.nekoverse.me/api/v1' \
  --name 'neko-echo-api' \
  nekoverse/neko-echo-api:latest
```

**Use**
```sh
curl "http://localhost:3666/api/v1/test?limit=100&skip=50&q=test" | jq
```

## References

- [User Guide](https://docs.micronaut.io/4.7.6/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.7.6/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.7.6/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)
- [Docker Plugin](https://guides.micronaut.io/latest/micronaut-push-to-oracle-cloud-container-registry-gradle-java.html)
