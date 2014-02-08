schedulingAPI
=============

* Install java (I am using java version 1.6.0_38)
* Install maven

* Clone the repo into $repo_dir

* mvn tomcat:run
(You can specify a different PORT as follows: mvn tomcat:run -Dmaven.tomcat.port=PORT)

* Open a browser to http://localhost:PORT/schedulingapi/

usage
=====

UI can be used to create vendors and activities

API must be used to add availability, create bookings and execute queries

Dates must be of the form MM/DD/YYYY HH24:mm i.e 02/08/2014 14:00 for 2/8/14 2pm


http://localhost:8081/activitys/addAvailability/{activityID}
Content-Type application/json
{ "date" : "02/08/2014 14:00", "capacity": "5", "cost": "20.0" }

http://localhost:8081/activitys/book/{activityID}
Content-Type application/json
{ "date" : "02/08/2014 14:00"}

http://localhost:8081/activitys/queryRange/{id}
Content-Type application/json
{ "startDate" : "02/08/2014 14:00", "endDate" : "02/08/2014 14:00"}

http://localhost:8081/activitys/queryDate/{id}
Content-Type application/json
{ "startDate" : "02/08/2014 14:00"}