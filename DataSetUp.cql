

<****************************************** Data SetUp For Demo Project Start *******************************************************>

CREATE KEYSPACE krogerks_ecom WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}  AND durable_writes = true;

CREATE TABLE krogerks_ecom.user (
    userid text PRIMARY KEY,
    first_name text,
    last_name text,
    type text
) 

insert into user(userid,first_name,last_name,type) values('U1','Praveen','Neelamegam','Prime');
insert into user(userid,first_name,last_name,type) values('U2','Rahul','Kumar','Non-Prime');
insert into user(userid,first_name,last_name,type) values('U3','Ajith','Kumar','Non-Prime');
insert into user(userid,first_name,last_name,type) values('U4','Sathish','Kumar','Prime');

CREATE TABLE krogerks_ecom.products (
    prod_name text,
    prod_id text,
    prod_desc text,
    prod_price decimal,
    PRIMARY KEY (prod_name, prod_id)
) 

CREATE INDEX prod_desc ON krogerks_ecom.products (prod_desc);
CREATE INDEX prod_price ON krogerks_ecom.products (prod_price);

insert into products(prod_name,prod_id,prod_desc,prod_price) values('Shirts','D1','Wrogn Shirts',600);
insert into products(prod_name,prod_id,prod_desc,prod_price) values('Shirts','D2','Arrow Shirts',500);
insert into products(prod_name,prod_id,prod_desc,prod_price) values('Mobile','M1','Nokia 1232',6000);

CREATE TABLE krogerks_ecom.prod_by_cat (
    catname text,
    catid text,
    prod_list map<text, text>,
    PRIMARY KEY (catname, catid)
) 
CREATE INDEX find_by_super_column_values ON krogerks_ecom.prod_by_cat (values(prod_list));
CREATE INDEX find_by_super_column_key ON krogerks_ecom.prod_by_cat (keys(prod_list));
CREATE INDEX find_by_super_column_entries ON krogerks_ecom.prod_by_cat (entries(prod_list));

insert into prod_by_cat(catname,catid,prod_list) values('Fashion','C1',{'P1':'Shirt','P2':'Jeans'});
insert into prod_by_cat(catname,catid,prod_list) values('Fashion','C2',{'P11':'Tops','P2':'Jeans'});
insert into prod_by_cat(catname,catid,prod_list) values('Electronic','C3',{'P21':'Mobile','P22':'Laptops'});


<******************************************** Data SetUp For Demo Project End *************************************************>


CREATE TYPE krogerks_ecom.address (
    city text,
    state text,
    zipcode decimal
);

CREATE TABLE krogerks_ecom.status (
    user_id text,
    status_id text,
    status text,
    PRIMARY KEY (user_id, status_id)
) 

CREATE TABLE krogerks_ecom.itemscounter (
    user_id text,
    product_id text,
    page_count counter,
    PRIMARY KEY (user_id, product_id)
) 

CREATE TABLE krogerks_ecom.user_information (
    user_id text PRIMARY KEY,
    phone_no list<int>,
    user_name text
) 
CREATE INDEX username_index ON krogerks_ecom.user_information (user_name);

CREATE TABLE krogerks_ecom.krogerweblogs (
    page_id uuid,
    page_name text,
    page_count counter,
    PRIMARY KEY (page_id, page_name)
) 

CREATE TABLE krogerks_ecom.prod_by_cat (
    catname text,
    catid text,
    prod_list map<text, text>,
    PRIMARY KEY (catname, catid)
) 

CREATE INDEX find_by_super_column_values ON krogerks_ecom.prod_by_cat (values(prod_list));
CREATE INDEX find_by_super_column_key ON krogerks_ecom.prod_by_cat (keys(prod_list));
CREATE INDEX find_by_super_column_entries ON krogerks_ecom.prod_by_cat (entries(prod_list));

CREATE TABLE krogerks_ecom.weblogs (
    page_id uuid,
    page_name text,
    insertion_time timestamp,
    page_count counter,
    PRIMARY KEY ((page_id, page_name), insertion_time)
) 

CREATE TABLE krogerks_ecom.users_and_carts (
    user_id text,
    cart_id text,
    address address,
    cart_name text,
    first_name text,
    last_name text,
    type text,
    wish_list map<text, text>,
    PRIMARY KEY (user_id, cart_id)
) 

CREATE TABLE krogerks_ecom.deals_and_offers (
    offer_id text,
    prod_id text,
    validity timestamp,
    discount_percent decimal,
    prod_name text,
    PRIMARY KEY (offer_id, prod_id, validity)
) 

CREATE TABLE krogerks_ecom.product_liked_by_user (
    user_id text,
    prod_id text,
    prod_name text,
    prod_price decimal,
    PRIMARY KEY (user_id, prod_id)
) 

CREATE TABLE krogerks_ecom.user_comments_by_product (
    prod_id text,
    user_id text,
    comments text,
    first_name text,
    last_name text,
    PRIMARY KEY (prod_id, user_id)
) 
CREATE TABLE krogerks_ecom.products_by_recomands (
    rating float,
    discount decimal,
    prod_desc text,
    prod_name text,
    PRIMARY KEY (rating, discount)
) 
CREATE TABLE krogerks_ecom.recently_viewed_items (
    user_id text,
    prod_name text,
    viewed counter,
    PRIMARY KEY (user_id, prod_name)
) 


CREATE INDEX type ON krogerks_ecom.user (type);

CREATE TABLE krogerks_ecom.products_by_recommends (
    rating float,
    discount decimal,
    prod_desc text,
    prod_name text,
    PRIMARY KEY (rating, discount)
) 

