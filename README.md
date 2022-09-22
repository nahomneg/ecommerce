Name of members
- Nahom Negussie Denessie 614733
- Hassan Abdelrahman Ali Ahmed 614159
- Awais iftikhar 614184

## How to run our project

We attached a video demo for our project
- Make sure you have docker setup on your machine
- Make sure you have kubernetes setup on your machine
- Demo Video Link:
  https://drive.google.com/file/d/13LMHirFOSg6FpQAV-virQTRTsyfGUxZ1/view


###commands

for this project you don't need to build the container by your self we arleady push the container to docker hub, and
if you run the following command
>> kubectl apply -f ./  #everything will start run on your machine

>> http://localhost:8181/accounts/register create an account

>> http://localhost:8181/authenticate using username and password of the created account using Post meethod to generate TWT token

for every request use the generated token before except get all products
