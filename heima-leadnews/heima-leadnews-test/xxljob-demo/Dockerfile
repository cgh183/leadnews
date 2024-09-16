# 使用更轻量的Java运行环境作为基础镜像
FROM openjdk:8-jre-alpine

# 指定维护者信息
LABEL maintainer="myr"

# 将工作目录设置为/app
WORKDIR /app

# 将编译好的jar文件复制到/app目录下
COPY ./target/*.jar /app/app.jar

# 暴露8080端口
EXPOSE 8881

# 运行jar文件
ENTRYPOINT ["java","-jar","/app/app.jar"]
