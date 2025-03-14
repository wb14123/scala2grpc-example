
This is an example project for the library [Scala2grpc](https://github.com/wb14123/scala2grpc).

Requirements:

* Java 21
* sbt

Build:

```
sbt clean generateGRPCCode
sbt compile
```

Run server:

```
sbt "runMain me.binwang.scala2grpc.example.ExampleGrpcServer"
```
