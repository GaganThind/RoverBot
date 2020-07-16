# RoverBot
Spring RESTfull backend to provide basic navigation and current position API.

This implementation exposes 2 API end-points to the user.
1) First API: This API takes the current position of the Bot and directions to move and returns the final position of the BOT. API end-point call should be made to URL (<localhost/URL>/api/v1/roverbot). The API accepts a POST method and the JSON payload and returns the final position of the roverbot. Below is the sample input that this API call expects.

Sample Input:

{
   "Position":{
      "Direction":"N",
      "X":10,
      "Y":10
   },
   "Move":[
      {
         "O":"1",
         "L":90,
         "F":10
      },
      {
         "O":"2",
         "R":180,
         "B":20
      }
   ]
}

Sample Output:

{
   “Position”: {
                 “Direction”: “E”,
                  “X” : “-20”,
                  “Y”: “10”
               }
}

Here Position attribute denotes the current position of the roverbot and Move attribute denotes the moves to be walked by the roverbot.

2) Second API: This will read the current position of the bot and return it as API response. API end-point call should be made to URL (<localhost/URL>/api/v1/roverbot). The API accepts a GET method and returns the current position of the roverbot.

Sample Output:

{
   “Position”: {
                 “Direction”: “E”,
                  “X” : “-20”,
                  “Y”: “10”
               }
}
