# Task Description

Your domain area is air company. You will have following types of planes:

- Military Plane.
- Passenger Plane.

**1. You are requested to implement reading/writing from/to different data sources for a given domain. You need to implement 
working with following data sources:**
- Property file.
- JSON file.
- XML file. 

**2. You are requested to use following types of exceptions:**
- DataSourceFileNotExistsException.java - in case XML, JSON, Property file is not present by defined system path.
- UbableToParseDataSourceException.java - in case of any parsing error of JSON or XML(e.g. not XML file(plain text)).
- UbableToWriteDataToSourceException.java - in case of any writing error. This exception also need to be thrown in case of we pass NULL object for writing.
---
###### Testing flow:
##### Read
Tests will generate sample of XML, JSON, Property and call your correspondent reader passing following info into constructor:
- Absolute path to the file. 
##### Write
Tests will generate expected XML, JSON, Property file. Then it will create correspondent writer passing following info into constructor:
- Absolute path to the file. 

After that it will call write method and pass as argument following info:
- Aircompany object to write into file.

Finally test will compare generated expected file with file that was created as result of calling write method of correspondent writer.

###### You are allowed:

- Create new classes. 
- Add new dependencies in pom.xml.
- Add annotations to existing classes (over fields and classes).
- Modify classes which is designed for your implementation (marked with throw new UnsupportedOperationException("You need to implement this logic");)

###### You are NOT allowed:

- Delete existing classes. 
- Delete existing dependencies in pom.xml.
- Move existing classes to another package.

---
##### JSON example
Json  represents JSON Array of planes. For Military planes field "Capacity" is always NULL (not applicable for that type of plane).
For passenger plane field "MilitaryType" is always NULL. To identify type of plane use "Type" field.

```
[
  {
    "MilitaryType": "BOMBER",
    "Speed": 1050,
    "Type": "military",
    "Capacity": null,
    "Model": "B-1B Lancer",
    "Distance": 21000
  },
  {
    "MilitaryType": null,
    "Speed": 940,
    "Type": "passenger",
    "Capacity": 192,
    "Model": "Boeing-737-800",
    "Distance": 63870
  }
```

##### XML example
Main xml element is <planes>. This element has two types of child elements \<MilitaryPlane> and \<PassengerPlane> depending on type of plane.
Military plan doesn't have <Capacity> field. Passenger plane doesn't have <MilitaryType> field. 

```
<?xml version="1.0" encoding="UTF-8"?>
<planes>
    <MilitaryPlane model="B-1B Lancer">
        <MilitaryType>BOMBER</MilitaryType>
        <Speed>1050</Speed>
        <Distance>21000</Distance>
    </MilitaryPlane>
    <PassengerPlane model="Boeing-737">
        <Capacity>164</Capacity>
        <Speed>900</Speed>
        <Distance>60500</Distance>
    </PassengerPlane>
</planes>
```

##### Property example
Property file represent list of planes. Each plane has set of properties which starts with plane${index}. For Military planes property "capacity" is missed (not applicable for that type of plane).
For passenger planes property "militaryType" is missed (not applicable for that type of plane).

```
#Thu Nov 26 23:10:52 MSK 2020

#military planes
plane0.distance=21000
plane0.militaryType=BOMBER
plane0.model=B-1B Lancer
plane0.speed=1050
plane0.type=military

#passenger planes
plane1.capacity=222
plane1.distance=80500
plane1.model=Airbus A330
plane1.speed=990
plane1.type=passenger

plane2.capacity=188
plane2.distance=65500
plane2.model=Airbus A320
plane2.speed=930
plane2.type=passenger
```


