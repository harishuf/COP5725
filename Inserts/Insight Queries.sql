1. Best and Worst selling Manufacturers.
========================================

select man.manufacturer_name, count(*) from manufacturer man
inner join model m on man.MANUFACTURER_ID = m.MANUFACTURER_ID
inner join used_car u on m.model_id = u.model_id
inner join listing l on u.registration_number=l.registration_number and status='sold'
group by man.manufacturer_name
order by count(*) desc;


2. Best and worst selling Models of a particular Brand
======================================================
select model_name,count(*) from model m 
inner join used_car u on m.model_id = u.model_id
inner join listing l on u.registration_number=l.registration_number and status='sold'
inner join  manufacturer man on m.manufacturer_id=man.manufacturer_id where man.manufacturer_name='volkswagen'
group by model_name
order by count(*) desc;


3. Average price per Manufacturer &Model
=========================================

select man.manufacturer_name,m.model_name,cast(avg(l.price) as decimal(10)) as avg_price from model m
join used_car u on u.model_id=m.model_id
join listing l on u.registration_number=l.registration_number
join manufacturer man on m.manufacturer_id=man.manufacturer_id
group by man.manufacturer_name,m.model_name
order by avg_price desc;


4. Preferred Attribute Combo in each Model
=============================================

select max(CNT),model_name,fuel_type,gearbox_type,vehicle_type from (
select count(*) as CNT, m.model_name,m.fuel_type, m.vehicle_type,m.gearbox_type from model m
join used_car u on m.model_id=u.MODEL_ID
left join listing l on u.registration_number=l.registration_number where status='sold'
group by m.model_name,m.fuel_type,m.vehicle_type, m.gearbox_type) m1
where model_name=m1.model_name 
group by model_name,model_name,fuel_type,gearbox_type,vehicle_type
order by max(CNT) desc;

5. Count of Cars and Manufacturers by User Region
============================================
select manufacturer_name,model_name,state, count(*) as cars_per_region from user_detail u
left outer join listing l on u.email_id=l.email_id
left outer  join used_car uc on l.registration_number=uc.registration_number
left outer join model m on uc.model_id=m.model_id
left outer join manufacturer man on m.manufacturer_id=man.manufacturer_id
group by manufacturer_name,model_name,state
order by manufacturer_name,state,cars_per_region desc;

6. Average of Price by Registration year
========================================

select registration_year,manufacturer_name,model_name, avg(price) as privar from used_car u
inner join model m on u.model_id=m.model_id
inner join listing l on u.registration_number=l.registration_number
inner join manufacturer mn on m.manufacturer_id=mn.manufacturer_id
where registration_year between '1990' and '2015'
group by registration_year,manufacturer_name,model_name
order by manufacturer_name,model_name;

7. Cheapest 10 Users
==============================

select * from (
select first_name,last_name,max(price), min(price), cast(avg(price) as decimal(10,2)) from user_detail ud
join listing l on ud.email_id=l.email_id 
group by first_name,last_name
order by avg(price) asc)
where rownum<11;

8. Query to Count all Tuples and display as one
===================================================

SELECT SUM(c)
FROM (
  SELECT COUNT(*) AS c FROM listing 
  UNION ALL
  SELECT COUNT(*) FROM model 
  UNION ALL
  SELECT COUNT(*) FROM user_detail 
  UNION ALL
  SELECT COUNT(*) from manufacturer
  UNION ALL
  SELECT COUNT(*) from used_car
);

9. Slow moving Cars
====================

select manufacturer_name,model_name,cast(avg(extract(day from date_sold-date_created))as decimal(10)) as avg_days_on_website from model m
join manufacturer man on m.manufacturer_id=man.manufacturer_id
join used_car u on m.model_id=u.model_id
join listing l on u.registration_number=l.registration_number where status='sold'
group by manufacturer_name,model_name
order by avg_days_on_website;

10. Accidents Vs Non Accidents
===============================

select T1.manufacturer_name,T1.price_with_accidents,T2.price_without_accidents from (
select manufacturer_name,cast(avg(price) as decimal(10,2)) as price_with_accidents from manufacturer mn
inner join model m on mn.manufacturer_id=m.manufacturer_id
inner join used_car u on u.model_id=m.model_id 
inner join listing l on u.registration_number=l.registration_number
where u.accident_history='yes'
group by manufacturer_name
order by price_with_accidents) T1 inner join (select manufacturer_name,cast(avg(price) as decimal(10,2)) as price_without_accidents from manufacturer mn
inner join model m on mn.manufacturer_id=m.manufacturer_id
inner join used_car u on u.model_id=m.model_id 
inner join listing l on u.registration_number=l.registration_number
where u.accident_history='no'
group by manufacturer_name
order by price_without_accidents) T2 on T1.manufacturer_name=T2.manufacturer_name;

Fuzzy Search:

create index listing_fuzzy on listing(title) indextype is ctxsys.context;

select * from listing where contains(listing.title,'definescore(fuzzy(golf,,,weight),relevance)',1)>0;

select * from listing where contains(listing.title,'definescore(fuzzy(for,60,,weight),relevance)',1)>0;


1. Brand that has oldest registration dates but have most number of sold.