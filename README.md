Welcome to Mplatform

This project is a showcase for a java spring boot application.

Live demo of the applicaiton can be found [here](http://mplatform.dejanroshkovski.com)
================================================

Currently the appplicaiton uses in memory authentication with basic auth.

An example user for testing the API would be 
username: admin
password: admin

## Available REST apis

### Doctor

``` GET /api/doctor/get/{id} ```
Gets the doctor by ID

``` POST /api/doctor/store ```
Stores a new doctor

``` DELETE /api/doctor/delete/{id} ```
Deletes doctor by ID

``` PATCH /api/doctor/update/{id} ```
Updates a doctor

``` GET /api/doctor/findByName/{inputString} ```
Search for a doctor by Name (First or Last)

``` GET /api/doctor/findByEmail/{inputString} ```
Search for a doctor by Email

### Patient

``` GET /api/patient/get/{id} ```
Gets the patient by ID

``` POST /api/patient/store ```
Stores a new patient

``` DELETE /api/patient/delete/{id} ```
Deletes patient by ID

``` PATCH /api/patient/update/{id} ```
Updates a patient

``` GET /api/patient/findByName/{inputString} ```
Search for a patient by Name (First or Last)

### Examination

``` GET /api/examination/get/{id} ```
Gets the examination by ID

``` POST /api/examination/store ```
Stores a new examination

``` DELETE /api/examination/delete/{id} ```
Deletes examination by ID

``` PATCH /api/examination/update/{id} ```
Updates a examination

``` GET /api/examination/findBySympthoms/{inputString} ```
Search for a examination by sympthoms

``` GET /api/examination/findByDiagnosis/{inputString} ```
Search for a examination by diagnosis

### TODO LIST:

- Implement token based authentication
- Implement DB based authentication
- Add mailsend feature for caught exceptions for 500 Errors
- Implement swagger