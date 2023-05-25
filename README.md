## Appointment - Simple JEE Application

### What is Appointment app?

The Appointment app observes following features:
- Integration with an external REST API (GET method) to retrieve data about public holidays in Serbia for year 2023.
- Expose CRUD methods as a REST API service to manage with Appointment. 
- Find all created Appointments that are on public holidays for given day

Note: Appointment consists of title, date, location and detail.

### Technologies
- Java 17
- Jakarta EE 10
- Maven 3.3.2
- Mongo DB 5.0
- Wildfly 27.0.1.Final
- Open API 3.0.1

### Setup & Review
#### Running Docker image
###### Application is developed in a such way that it can be started as simple as possible, just by running docker image.
`Prerequisite: Installed Docker on local machine`

When you receive / download docker image, apply following steps:
- Open location where is image file downloaded
- Open terminal on that location and run command:`docker load -i <docker_image_name>` (Should see message like: `Loaded image: my-app:latest`)
- Download [docker-compose file](https://github.com/gorandiv/simplejee/blob/main/docker-compose.yml) in the same directory.
- After image is loaded and docker-compose file is downloaded, in the same terminal run command: `docker-compose up`

After those steps application should be available for testing.
Postman Collection is available [here](https://drive.google.com/file/d/1ZJbxDNSfJVdUcJWm8OP1WdK1_2RKFMAJ/view?usp=drive_link)


### Review
- Source code is available on [Github](https://github.com/gorandiv/simplejee/tree/main)
- Database viewer (Mongo Express) is available on [localhost](http://localhost:8081/db/appointmentdb/appointment)
- Postman Collection is available [here](https://api.postman.com/collections/13864082-9786a227-ac22-4c6d-b04e-3f24bb15f5b8?access_key=PMAT-01H0E1ZJ9B51K15KX5CARRFMX5)
- [API specification](https://github.com/gorandiv/simplejee/blob/main/specification.json)