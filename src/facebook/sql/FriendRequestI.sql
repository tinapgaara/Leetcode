/*
597. Friend Requests I: Overall Acceptance Rate
DescriptionHintsSubmissionsDiscussSolution
 SQL SchemaPick One
In social network like Facebook or Twitter, people send friend requests and accept others’ requests as well. Now given two tables as below:
Table: friend_request
| sender_id | send_to_id |request_date|
|-----------|------------|------------|
| 1         | 2          | 2016_06-01 |
| 1         | 3          | 2016_06-01 |
| 1         | 4          | 2016_06-01 |
| 2         | 3          | 2016_06-02 |
| 3         | 4          | 2016-06-09 |
Table: request_accepted
| requester_id | accepter_id |accept_date |
|--------------|-------------|------------|
| 1            | 2           | 2016_06-03 |
| 1            | 3           | 2016-06-08 |
| 2            | 3           | 2016-06-08 |
| 3            | 4           | 2016-06-09 |
| 3            | 4           | 2016-06-10 |
Write a query to find the overall acceptance rate of requests rounded to 2 decimals, which is the number of acceptance divide the number of requests.
For the sample data above, your query should return the following result.
|accept_rate|
|-----------|
|       0.80|


Approach: Using round and ifnull [Accepted]
Intuition

Count the accepted requests and then divides it by the number of all requests.

Algorithm

To get the distinct number of accepted requests, we can query from the request_accepted table.

select count(*) from (select distinct requester_id, accepter_id from request_accepted;
With the same technique, we can have the total number of requests from the friend_request table:

select count(*) from (select distinct sender_id, send_to_id from friend_request;
At last, divide these two numbers and round it to a scale of 2 decimal places to get the required acceptance rate.

Wait! The divisor (total number of requests) could be '0' if the table friend_request is empty. So, we have to utilize ifnull to deal with this special case.
*/


# Write your MySQL query statement below
# select ... from request, so count(distinct requester_id, accepter_id) dont need to from table.

select
  case when (select count(distinct sender_id, send_to_id) from friend_request)=0 then 0.00
  else round(count(distinct requester_id, accepter_id) /(select count(distinct sender_id, send_to_id) from friend_request), 2) end as accept_rate
from request_accepted;

select
 round(
     ifnull((select count(distinct requester_id, accepter_id) from request_accepted)
              / (select count(distinct sender_id, send_to_id) from friend_request), 0),
     2
 )
as accept_rate