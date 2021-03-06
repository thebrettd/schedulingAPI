schedulingAPI
=============

* Install java (I am using java version 1.6.0_38)
* Install maven

* Clone the repo into $repo_dir

* mvn tomcat:run
(You can specify a different PORT as follows: mvn tomcat:run -Dmaven.tomcat.port=PORT)

* Open a browser to http://localhost:PORT/schedulingapi/

Usage
=====

UI can be used to create vendors and activities. Convenience methods exist so vendors/activities can be easily created
through the API as well.

API must be used to add availability, create bookings and execute queries

Dates must be of the form MM/DD/YYYY HH24:mm i.e 02/08/2014 14:00 for 2/8/14 2pm

The scripts in /schedulingAPI/src/test/curl can be used to manually test any of the API calls. Note that the script
assumes the process is listening on port 8081

Requests
======

#AddAvailability
```
http://localhost:8081/activitys/addAvailability/{activityID}

Content-Type application/json

{ "date" : "02/08/2014 14:00", "capacity": "5", "cost": "20.0" }
```
#book
```
http://localhost:8081/activitys/book/{activityID}

Content-Type application/json

{ "date" : "02/08/2014 14:00"}
```
#queryRange

```
http://localhost:8081/activitys/queryRange/{id}

Content-Type application/json

{ "startDate" : "02/08/2014 14:00", "endDate" : "02/08/2014 14:00"}
```

#queryDate
```
http://localhost:8081/activitys/queryDate/{id}

Content-Type application/json

{ "startDate" : "02/08/2014 14:00"}
```

#addVendor
```
http://localhost:8081/vendors/addVendor

Content-Type application/json

{ "name" : "Vendor1"}
```

#addActivity
```
http://localhost:8081/vendors/addActivity

Content-Type application/json

{ "name" : "Vendor1"}
```