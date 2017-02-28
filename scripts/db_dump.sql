--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: ecommerceDev1; Type: DATABASE; Schema: -; Owner: ecommerceDev1
--

-- CREATE DATABASE "ecommerceDev1" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


-- ALTER DATABASE "ecommerceDev1" OWNER TO "ecommerceDev1";

-- \connect "ecommerceDev1"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

-- COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE categories (
    category_id bigint NOT NULL,
    parent_id bigint,
    name character varying(100) NOT NULL,
    description character varying(1000)
);


-- ALTER TABLE categories OWNER TO "ecommerceDev1";

--
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE categories_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE categories_category_id_seq OWNER TO "ecommerceDev1";

--
-- Name: categories_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE categories_category_id_seq OWNED BY categories.category_id;


--
-- Name: characteristic_groups; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE characteristic_groups (
    characteristic_group_id bigint NOT NULL,
    name character varying(100) NOT NULL
);


-- ALTER TABLE characteristic_groups OWNER TO "ecommerceDev1";

--
-- Name: characteristic_groups_characteristic_group_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE characteristic_groups_characteristic_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE characteristic_groups_characteristic_group_id_seq OWNER TO "ecommerceDev1";

--
-- Name: characteristic_groups_characteristic_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE characteristic_groups_characteristic_group_id_seq OWNED BY characteristic_groups.characteristic_group_id;


--
-- Name: characteristic_values; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE characteristic_values (
    characteristic_id bigint NOT NULL,
    product_id bigint NOT NULL,
    value character varying(255) NOT NULL
);


-- ALTER TABLE characteristic_values OWNER TO "ecommerceDev1";

--
-- Name: characteristics; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE characteristics (
    characteristic_id bigint NOT NULL,
    category_id bigint NOT NULL,
    name character varying(100) NOT NULL,
    characteristic_group_id bigint NOT NULL,
    filterable boolean DEFAULT false
);


-- ALTER TABLE characteristics OWNER TO "ecommerceDev1";

--
-- Name: characteristics_characteristic_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE characteristics_characteristic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE characteristics_characteristic_id_seq OWNER TO "ecommerceDev1";

--
-- Name: characteristics_characteristic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE characteristics_characteristic_id_seq OWNED BY characteristics.characteristic_id;


--
-- Name: discount; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE discount (
    discount_id bigint NOT NULL,
    name character varying(100) NOT NULL,
    value smallint NOT NULL
);


-- ALTER TABLE discount OWNER TO "ecommerceDev1";

--
-- Name: discount_discount_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE discount_discount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE discount_discount_id_seq OWNER TO "ecommerceDev1";

--
-- Name: discount_discount_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE discount_discount_id_seq OWNED BY discount.discount_id;


--
-- Name: order_items; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE order_items (
    product_id bigint NOT NULL,
    sales_order_id bigint NOT NULL,
    quantity integer NOT NULL,
    standard_price bigint
);


-- ALTER TABLE order_items OWNER TO "ecommerceDev1";

--
-- Name: order_statuses; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE order_statuses (
    order_status_id bigint NOT NULL,
    name character varying(100) NOT NULL
);


-- ALTER TABLE order_statuses OWNER TO "ecommerceDev1";

--
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE order_statuses_order_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE order_statuses_order_status_id_seq OWNER TO "ecommerceDev1";

--
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE order_statuses_order_status_id_seq OWNED BY order_statuses.order_status_id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE products (
    product_id bigint NOT NULL,
    category_id bigint NOT NULL,
    name character varying(250) NOT NULL,
    description character varying(1000) NOT NULL,
    discount_id smallint,
    price numeric(12,2) NOT NULL
);


-- ALTER TABLE products OWNER TO "ecommerceDev1";

--
-- Name: products_product_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE products_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE products_product_id_seq OWNER TO "ecommerceDev1";

--
-- Name: products_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE products_product_id_seq OWNED BY products.product_id;


--
-- Name: properties; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE properties (
    property_id character varying(100) NOT NULL,
    value character varying(500) NOT NULL
);


-- ALTER TABLE properties OWNER TO "ecommerceDev1";

--
-- Name: recommended_products; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE recommended_products (
    source_product_id bigint NOT NULL,
    target_product_id bigint NOT NULL
);


-- ALTER TABLE recommended_products OWNER TO "ecommerceDev1";

--
-- Name: reviews; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE reviews (
    product_id bigint NOT NULL,
    user_id bigint NOT NULL,
    description character varying(3000) NOT NULL,
    creation_date date NOT NULL,
    raiting smallint NOT NULL,
    CONSTRAINT raiting_check_for_ten CHECK (((raiting >= 0) AND (raiting <= 10)))
);


-- ALTER TABLE reviews OWNER TO "ecommerceDev1";

--
-- Name: roles; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE roles (
    role_id bigint NOT NULL,
    name character varying(100) NOT NULL
);


-- ALTER TABLE roles OWNER TO "ecommerceDev1";

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE roles_role_id_seq OWNER TO "ecommerceDev1";

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE roles_role_id_seq OWNED BY roles.role_id;


--
-- Name: sales_orders; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE sales_orders (
    sales_order_id bigint NOT NULL,
    user_id bigint NOT NULL,
    creation_date date NOT NULL,
    "limit" numeric(12,2),
    order_status_id bigint NOT NULL
);


-- ALTER TABLE sales_orders OWNER TO "ecommerceDev1";

--
-- Name: sales_orders_sales_order_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE sales_orders_sales_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE sales_orders_sales_order_id_seq OWNER TO "ecommerceDev1";

--
-- Name: sales_orders_sales_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE sales_orders_sales_order_id_seq OWNED BY sales_orders.sales_order_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: ecommerceDev1
--

CREATE TABLE users (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    first_name character varying,
    last_name character varying,
    password character varying(1000) NOT NULL,
    phone character varying,
    email character varying(100) NOT NULL,
    registration_date date,
    recovery_hash character varying(10),
    user_avatar character varying DEFAULT '/images/useravatars/unknownuser/unknownuser.png'::character varying
);


-- ALTER TABLE users OWNER TO "ecommerceDev1";

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: ecommerceDev1
--

CREATE SEQUENCE users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE users_user_id_seq OWNER TO "ecommerceDev1";

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ecommerceDev1
--

ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;


--
-- Name: categories category_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY categories ALTER COLUMN category_id SET DEFAULT nextval('categories_category_id_seq'::regclass);


--
-- Name: characteristic_groups characteristic_group_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristic_groups ALTER COLUMN characteristic_group_id SET DEFAULT nextval('characteristic_groups_characteristic_group_id_seq'::regclass);


--
-- Name: characteristics characteristic_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristics ALTER COLUMN characteristic_id SET DEFAULT nextval('characteristics_characteristic_id_seq'::regclass);


--
-- Name: discount discount_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY discount ALTER COLUMN discount_id SET DEFAULT nextval('discount_discount_id_seq'::regclass);


--
-- Name: order_statuses order_status_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY order_statuses ALTER COLUMN order_status_id SET DEFAULT nextval('order_statuses_order_status_id_seq'::regclass);


--
-- Name: products product_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY products ALTER COLUMN product_id SET DEFAULT nextval('products_product_id_seq'::regclass);


--
-- Name: roles role_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY roles ALTER COLUMN role_id SET DEFAULT nextval('roles_role_id_seq'::regclass);


--
-- Name: sales_orders sales_order_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY sales_orders ALTER COLUMN sales_order_id SET DEFAULT nextval('sales_orders_sales_order_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY users ALTER COLUMN user_id SET DEFAULT nextval('users_user_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY categories (category_id, parent_id, name, description) FROM stdin;
1	\N	Computers	Main Computers Category
2	1	Laptops	Portable power in your hands for on-the-go work and play.
6	5	Video game console	Main Video Games Category
7	5	Games	Main Video Games Category
5	\N	Video Games	Main Video Games Category
3	1	Desktops	Shop sleek towers, complete packages, or compact all-in-one designs.
4	1	Tablets	Go-anywhere devices in your choice of brands, operating systems and sizes.
\.


--
-- Name: categories_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('categories_category_id_seq', 43, true);


--
-- Data for Name: characteristic_groups; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY characteristic_groups (characteristic_group_id, name) FROM stdin;
1	Specifications
2	Display
3	General
4	Other
5	Overview
\.


--
-- Name: characteristic_groups_characteristic_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('characteristic_groups_characteristic_group_id_seq', 10, true);


--
-- Data for Name: characteristic_values; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY characteristic_values (characteristic_id, product_id, value) FROM stdin;
1	1	HP
3	1	Intel 7th Generation Core i5, 3.3 gigahertz
5	1	Windows 10
6	1	AMD RADEON 6550M
7	1	1920 x 1080 (Full HD)
8	1	15.6 inches
9	1	Full HD Widescreen LED
10	1	Silver
11	1	0.7x15x9.8 inches
12	1	4.78 pounds
13	1	m6-aq103dx
14	1	Lithium-ion
15	1	microSD
1	2	Apple
3	2	Intel, 3.3 gigahertz
4	2	8 gigabytes
5	2	Mac OS
6	2	Intel Iris Graphics 550
8	2	13.3 inches
9	2	Other
10	2	Silver
11	2	0.59x11.97x8.36 inches
12	2	3.02 pounds
13	2	MNQG2LL/A
14	2	Lithium-polymer
15	2	No
1	3	Samsung
3	3	Intel Celeron, 1.6 gigahertz
4	3	4 gigabytes
5	3	Chrome OS
6	3	Intel® HD Graphics
7	3	1366 x 768 (HD)
8	3	11.6 inches
9	3	Widescreen LED
10	3	Black
11	3	0.7x11.37x8.04 inches
12	3	2.54 pounds
13	3	XE500C13-K02US
14	3	Lithium-ion
15	3	microSD
1	4	iBUYPOWER
3	4	Intel 7th Generation Core i7, 3.4 gigahertz
4	4	16 gigabytes
5	4	Windows 10
6	4	NVIDIA GeForce GTX 1060 graphics, 3Gb
10	4	Multi
11	4	17x7.9x17.5 inches
12	4	30 pounds
13	4	BB921
1	5	Apple
3	5	Intel, 2.8 gigahertz
4	5	8 gigabytes
5	5	Windows 10
6	5	Intel® Iris Pro Graphics 6200
10	5	Silver
11	5	21.26x25.2x8.9 inches
12	5	18.298 pounds
13	5	MK442LL/A
1	6	DELL
3	6	AMD A8-Series, 2.2 gigahertz
4	6	8 gigabytes
5	6	Windows 10
6	6	AMD Radeon R5
7	6	1920 x 1080 (Full HD)
8	6	23.8 inches
9	6	Touchscreen LED
10	6	Black/White
11	6	14.25x22.7x1.51 inches
12	6	16.47 pounds
13	6	I3455-10041WHT
1	7	Samsung
3	7	Intel Atom, 1.3 gigahertz
5	7	Android 5.1 Lollipop
7	7	1280 x 800
8	7	7 inches
10	7	Silver
11	7	7.4x4.3x0.3 inches
12	7	10 ounces
13	7	SM-T280NZSAXAR
14	7	4000 milliampere hours
15	7	microSDXC
1	8	Apple
3	8	Apple A8X
5	8	Apple iOS 8.1
7	8	2048 x 1536
8	8	9.7 inches
10	8	Gold
11	8	9.4x6.6 inches
12	8	7 ounces
13	8	MNV72LL/A
14	8	Lithium-ion
15	8	No
1	9	DigiLand
3	9	Intel Atom 3735F, 1.3 gigahertz
5	9	Windows 10
7	9	1280 x 800
8	9	8 inches
10	9	Black
11	9	0.4x8.2x4.9 inches
12	9	13.44 ounces
13	9	DL808W
14	9	Lithium-polymer
15	9	microSD
16	10	Sony
17	10	1000 gigabytes
18	10	3
19	10	1
20	10	Bluetooth, Wi-Fi
21	10	Yes, HDD
22	10	DUALSHOCK 4 wireless controller; Mono headset; Premium HDMI cable; USB and power cables
23	10	SONY PLAYSTATION 4 PRO CONSOLE
24	10	Black
16	11	Microsoft
17	11	500 gigabytes
18	11	3
19	11	1
20	11	Wi-Fi
21	11	Yes, HDD
22	11	HDMI Cable, AC Power cable, Xbox Live Gold 14-day membership trial, Battlefield 1 full game download, Xbox Wireless Controller
23	11	Xbox One S 500GB Battlefield™ 1 Console Bundle
24	11	White
4	1	12 21gigabytes
25	12	Final Fantasy XV Day One Edition - PlayStation 4
26	12	Fight the imperial army to reclaim Eos in Final Fantasy XV. You are the crown prince who has been cast aside, so you must travel through Eos while battling extraordinary new monsters, making friends and discovering your skills.
27	12	Enter the world of FINAL FANTASY XV, and experience epic action-packed battles along your journey of discovery. 
25	13	Battlefield 1 - PlayStation 4
26	13	Battle players in new war environments with Battlefield 2016 for the PlayStation 4. 
27	13	Be a part of the greatest battles ever known to man. From the heavily defended Alps to the scorching deserts of Arabia, war is raging on an epic scale on land, air and sea.
25	14	Battlefield 1 - Xbox One
26	14	Battle players in new war environments with Battlefield 2016 for the Xbox One. 
27	14	Be a part of the greatest battles ever known to man. From the heavily defended Alps to the scorching deserts of Arabia, war is raging on an epic scale on land, air and sea.
25	15	Call of Duty: Infinite Warfare - Xbox One
26	15	Infinity Ward, the award-winning studio that helped create the blockbuster Call of Duty® franchise, reaches new heights with Call of Duty®: Infinite Warfare.
27	15	Call of Duty: Infinite Warfare will take players on an unforgettable journey as they engage in battles from Earth to beyond our atmosphere against a relentless, enemy faction that threatens our very way of life.
2	1	100000 gigabytes
2	2	100000 gigabytes
2	3	100000 gigabytes
2	4	100000 gigabytes
2	5	100000 gigabytes
2	6	100000 gigabytes
2	7	100000 gigabytes
2	8	100000 gigabytes
2	9	100000 gigabytes
28	5	\\images\\cat_id1\\cat_id3\\prod_id5\\imac.png
28	1	\\images\\cat_id1\\cat_id2\\prod_id1\\HP_ENVY_x360.png, \\images\\cat_id1\\cat_id2\\prod_id1\\HP_ENVY_x360.png
28	2	\\images\\cat_id1\\cat_id2\\prod_id2\\macbookpro.png, \\images\\cat_id1\\cat_id2\\prod_id2\\macbookpro.png,\\images\\cat_id1\\cat_id2\\prod_id2\\macbookpro.png
\.


--
-- Data for Name: characteristics; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY characteristics (characteristic_id, category_id, name, characteristic_group_id, filterable) FROM stdin;
28	1	Images	1	f
2	1	Hard Drive Capacity	1	f
4	1	Type of Memory (RAM)	1	f
5	1	Operating System	1	f
6	1	Graphics Cards	1	f
7	1	Screen Resolution	2	f
8	1	Screen Size	2	f
9	1	Display Type	2	f
10	1	Color	3	f
13	1	Model Number	4	f
18	5	Number Of USB Port(s)	1	f
19	5	Number Of HDMI Outputs	1	f
20	5	Wireless Network Compatibility	1	f
21	5	Internal Hard Drive	1	f
22	5	Additional Accessories Included	3	f
23	5	Model Number	4	f
24	5	Color	3	f
25	5	Name	5	f
1	1	Brand	1	t
3	1	Processor Model	1	t
11	1	Dimension	3	t
12	1	Weight	3	f
14	1	Battery Type	4	f
15	1	Expandable Memory Compatibility	4	f
26	5	Synopsis	5	f
16	5	Brand	1	t
17	5	Hard Drive Capacity	1	t
27	5	Product Features	5	f
\.


--
-- Name: characteristics_characteristic_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('characteristics_characteristic_id_seq', 54, true);


--
-- Data for Name: discount; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY discount (discount_id, name, value) FROM stdin;
1	No discount	0
2	5%	5
3	10%	10
4	15%	15
5	20%	20
6	25%	25
7	30%	30
8	35%	35
9	40%	40
10	45%	45
11	50%	50
12	60%	60
13	75%	75
14	80%	80
15	90%	90
16	Present	100
\.


--
-- Name: discount_discount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('discount_discount_id_seq', 32, true);


--
-- Data for Name: order_items; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY order_items (product_id, sales_order_id, quantity, standard_price) FROM stdin;
1	157	1	\N
1	152	1	\N
1	156	5	\N
1	154	2	\N
7	156	8	\N
5	154	1	\N
1	160	1	\N
10	159	1	240
7	160	4	\N
10	154	2	\N
1	159	1	480
\.


--
-- Data for Name: order_statuses; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY order_statuses (order_status_id, name) FROM stdin;
2	Submited
3	Completed
1	Entering
\.


--
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('order_statuses_order_status_id_seq', 8, true);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY products (product_id, category_id, name, description, discount_id, price) FROM stdin;
2	2	Apple - MacBook Pro	The new MacBook Pro is faster and more powerful than before, yet remarkably thinner and lighter.¹ It has the brightest, most colorful Mac notebook display ever. And it introduces the revolutionary Touch Bar—a Multi-Touch– enabled strip of glass built into the keyboard for instant access to what you want to do, when you want to do it. The new MacBook Pro is built on groundbreaking ideas. And it’s ready for yours.	1	1999.99
5	3	Apple - iMac®	iMac features a gorgeous widescreen display, powerful Intel® processors, superfast graphics, and more. All in a stunningly thin enclosure thats only 5 mm thin at its edge.	1	1299.99
6	3	Dell - Inspiron	Dell Inspiron All-In-One Computer: Glide your fingertips across the 23.8" touch screen to adjust music and video playback, surf the Web and play games, all in 1920 x 1080 resolution. Built-in Wi-Fi provides easy access to streaming entertainment.	1	649.99
8	4	Apple - iPad Air 2	The thinnest iPad ever is also the most capable. Its loaded with advanced technologies, including the Touch ID fingerprint sensor.	1	399.99
9	4	DigiLand - Tablet	DigiLand Tablet: Stay connected wherever you go with this 8" tablet. Windows 10 makes Web navigation a snap, and you can house plenty of photos, music and documents with 32GB of storage space. Plus, snap clear photos of events with 2.0MP front- and rear-facing cameras.	1	79.99
11	6	Microsoft - Xbox One S	Own the Xbox One S Battlefield™ 1 Bundle (500GB), featuring 4K Blu-ray™, 4K video streaming, High Dynamic Range, a full game download of Battlefield™ 1, and one month of EA Access.	1	299.99
12	7	Final Fantasy XV Day One Edition	Final Fantasy XV Day One Edition - PlayStation 4	1	59.99
14	7	Battlefield 1	Battlefield 1 - Xbox One	1	59.99
15	7	Call of Duty: Infinite Warfare	Call of Duty: Infinite Warfare - Xbox One	1	59.99
3	2	Samsung - Chromebook 3	Samsung Chromebook 3: Unleash the power of modern computing with this Samsung Chromebook laptop. It has all the space you need with the 11.6-inch screen, which ensures portability, and 4GB of RAM and an Intel Celeron processor lets you complete basic office tasks all day long. The 16GB storage capacity means this Samsung Chromebook laptop can store files and programs for offline use.	2	179.00
4	3	iBUYPOWER - Desktop	iBuyPower Desktop: Play hard with this iBUYPOWER gaming PC. With a 3.4GHz Intel i7 quad-core processor, 16GB of RAM and an NVIDIA GeForce 3GB graphics card, this machine can handle the most demanding adventures. This iBUYPOWER gaming PC has a 1TB SATA III hard drive and 120GB solid-state drive to provide ample file storage.	5	949.99
7	4	Samsung - Galaxy Tab A (2016)	Boost productivity with this Samsung Galaxy Tab A tablet. The 7-inch touch display provides the ideal compromise between portability and functionality, while the 8GB of internal memory lets you take photos, games, music and movies with you. The 4,000 mAh battery in this Samsung Galaxy Tab A delivers plenty of juice to get you through the day.	8	89.99
10	6	Sony - PlayStation 4 Pro	PS4 Pro gets you closer to your game. Heighten your experiences. Enrich your adventures. Let the super-charged PS4 Pro lead the way.	9	399.99
13	7	Battlefield 1	Battlefield 1 - PlayStation 4	4	59.99
1	2	HP - ENVY x360	HP ENVY x360 Convertible 2-in-1 Laptop: Bring versatility to your computing with this HP convertible laptop. The Intel Core i5 processor provides powerful performance when using your favorite programs and apps, and the 1TB hard drive lets you store plenty of digital files. This HP convertible laptop rotates a full 360 degrees for customized viewing of the 15.6-inch screen.	9	799.00
\.


--
-- Name: products_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('products_product_id_seq', 55, true);


--
-- Data for Name: properties; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY properties (property_id, value) FROM stdin;
About	Our Shop is a leading provider of technology products, services and solutions. The company offers expert service at an unbeatable price more than 1.5 billion times a year to the consumers, small business owners and educators who visit our stores. The shop has operations in the Russia where more than 70 percent of the population.
Email	inbox@shop.com
Phone	+1 888-222-1010
Rights	© 2005-2017, Shop, LLC. All rights reserved.
facebookUrl	https://facebook.com
google+Url	https://plus.google.com
twitterUrl	https://twitter.com
vkUrl	https://vk.com
\.


--
-- Data for Name: recommended_products; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY recommended_products (source_product_id, target_product_id) FROM stdin;
1	2
1	3
2	1
2	3
3	1
3	2
4	5
4	6
5	4
5	6
6	4
6	5
7	8
7	9
8	7
8	9
9	7
9	8
10	11
11	10
12	13
12	14
12	15
13	12
13	14
13	15
14	12
14	13
14	15
15	12
15	13
15	14
\.


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY reviews (product_id, user_id, description, creation_date, raiting) FROM stdin;
2	35	It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).ready for yours.	2017-02-22	5
1	78	so good	2017-02-23	4
1	69	It's ideal notebook!	2017-02-27	5
1	35	213	2017-02-27	5
1	4	Bad notepad, not working	2016-12-05	2
4	4	Bad PC, very big price	2016-12-05	1
5	4	Bad Mac, i hate him	2016-12-05	1
6	4	Bad PC, so many lags	2016-12-05	2
7	4	Bad tablet, Samsung bad company	2016-12-05	2
8	4	Bad tablet, i dont like apple production	2016-12-05	1
10	4	Sony, can only product TV	2016-12-05	2
11	4	I dont have money, then console is bad	2016-12-05	1
12	4	Bad game, I didnt have console	2016-12-05	2
13	4	Bad game, I didnt have console	2016-12-05	2
14	4	Bad game, I didnt have console	2016-12-05	2
15	4	Bad game, I didnt have console	2016-12-05	2
5	3	Nice Mac	2016-12-05	5
4	3	Nice PC	2016-12-05	5
8	3	Nice tablet	2016-12-05	5
2	3	Good iPad	2016-12-05	5
10	3	This console the best, thanks Sony	2016-12-05	5
9	4	Nice tablet, ill like this price	2016-12-05	4
12	3	Nice Game, I liked	2016-12-05	3
1	3	Good notepad	2016-12-05	4
14	3	Nice Game, I liked	2016-12-05	3
15	3	Nice Game, I liked	2016-12-05	3
13	3	Nice Game, I liked	2016-12-05	3
7	3	Nice tablet	2016-12-05	2
3	3	Good notepad	2016-12-05	2
6	3	Nice PC	2016-12-05	3
9	3	This tablet not interesting	2016-12-05	3
11	3	I dont like Microsoft	2016-12-05	3
3	4	Bad notepad, not working	2016-12-05	3
2	4	Bad notepad, not working iPad	2016-12-05	2
1	5	Very Good NoteBook.	2016-12-05	5
7	35	Boost productivity with this Samsung Galaxy Tab A tablet. The 7-inch touch display provides the ideal compromise between portability and functionality, while the 8GB of internal memory lets you take photos, games, music and movies with you. The 4,000 mAh battery in this Samsung Galaxy Tab A delivers plenty of juice to get you through the day.	2017-02-10	5
1	37		2017-02-21	2
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY roles (role_id, name) FROM stdin;
1	Administrators
2	Managers
3	Users
\.


--
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('roles_role_id_seq', 6, true);


--
-- Data for Name: sales_orders; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY sales_orders (sales_order_id, user_id, creation_date, "limit", order_status_id) FROM stdin;
158	41	2017-02-20	\N	1
155	69	2017-02-12	\N	1
152	3	2017-02-12	800.00	3
153	1	2017-02-12	\N	1
157	41	2017-02-13	\N	2
160	78	2017-02-21	3000.00	1
154	37	2017-02-12	\N	1
156	35	2017-02-12	\N	1
164	57	2017-02-28	\N	1
159	3	2017-02-20	1000.00	1
\.


--
-- Name: sales_orders_sales_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('sales_orders_sales_order_id_seq', 164, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: ecommerceDev1
--

COPY users (user_id, role_id, first_name, last_name, password, phone, email, registration_date, recovery_hash, user_avatar) FROM stdin;
36	3	a			\N	user@mail.ru	2017-01-18	4247867252	/images/useravatars/unknownuser/unknownuser.png
60	3	\N	\N	42767516990368493138776584305024125808	\N	user1234@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
40	3	Alex			\N		2017-01-25	\N	/images/useravatars/unknownuser/unknownuser.png
41	3	Bobffg	Gagaf	172615877915217560892406575915100721237	\N	lordever@mail.ru	2017-02-02	7683130118	/images/useravatars/unknownuser/unknownuser.png
4	3	max	max	316448663440605426369181748590440883182	444444	user@mail.ru	2016-12-05	\N	/images/useravatars/unknownuser/unknownuser.png
57	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
66	3	\N	\N	316448663440605426369181748590440883182	\N	user@admin.com	2017-02-11	\N	/images/useravatars/unknownuser/unknownuser.png
68	3	\N	\N	316448663440605426369181748590440883182	\N	user@mail.admin.ru	2017-02-12	\N	/images/useravatars/unknownuser/unknownuser.png
6	2	alexander	samon	62303972861003552617591658957390934968	79362673456	hdg@mail.ru	2016-12-12	\N	/images/useravatars/unknownuser/unknownuser.png
75	3	\N	\N	42767516990368493138776584305024125808	\N	4235@mail.ru	2017-02-14	\N	/images/useravatars/unknownuser/unknownuser.png
37	1	User	Test	12707736894140473154801792860916528374	\N	test@test.com	2017-01-19	\N	/images/useravatars/unknownuser/unknownuser.png
65	3	\N	\N	173447602773428053556316684567667297915	\N	kastiel63@gmail.com	2017-02-09	\N	/images/useravatars/unknownuser/unknownuser.png
5	1			173447602773428053556316684567667297915		test@email.ru	2016-12-05	\N	/images/useravatars/unknownuser/unknownuser.png
1	1	andrew	andrew	32254982671930602039565692807661004282	111111	admin@admin.com	2016-12-05	0154541624	/images/useravatars/unknownuser/unknownuser.png
69	3	Donald	Trump	172615877915217560892406575915100721237	\N	crab16_91@mail.ru	2017-02-12	\N	/images/useravatars/unknownuser/unknownuser.png
67	3	\N	\N	316448663440605426369181748590440883182	\N	admin@user.com	2017-02-12	\N	/images/useravatars/unknownuser/unknownuser.png
71	3	\N	\N	172615877915217560892406575915100721237	\N	test@testtest.com	2017-02-14	\N	/images/useravatars/unknownuser/unknownuser.png
56	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
3	3	boris	boris	316448663440605426369181748590440883182	333333	user@user.com	2016-12-05	1631121811	/images/useravatars/unknownuser/unknownuser.png
50	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
58	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
47	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
53	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
52	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
55	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
49	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
43	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
45	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
54	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
44	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
51	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
48	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
46	3	\N	\N	42767516990368493138776584305024125808	\N	user123@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
73	3	\N	\N	42767516990368493138776584305024125808	\N	user999@mail.ru	2017-02-14	\N	/images/useravatars/unknownuser/unknownuser.png
63	3	\N	\N	42767516990368493138776584305024125808	\N	user@mail.ru	2017-02-09	\N	/images/useravatars/unknownuser/unknownuser.png
42	3	\N	\N	42767516990368493138776584305024125808	\N	user@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
61	3	\N	\N	42767516990368493138776584305024125808	\N	user@mail.ru	2017-02-09	\N	/images/useravatars/unknownuser/unknownuser.png
59	3	\N	\N	42767516990368493138776584305024125808	\N	user@mail.ru	2017-02-08	\N	/images/useravatars/unknownuser/unknownuser.png
64	3	\N	\N	42767516990368493138776584305024125808	\N	123321@mail.ru	2017-02-09	\N	/images/useravatars/unknownuser/unknownuser.png
74	3	\N	\N	42767516990368493138776584305024125808	\N	12345678@mail.ru	2017-02-14	\N	/images/useravatars/unknownuser/unknownuser.png
62	3	\N	\N	42767516990368493138776584305024125808	\N	12345@mail.ru	2017-02-09	\N	/images/useravatars/unknownuser/unknownuser.png
33	3	\N	\N	42767516990368493138776584305024125808	\N	123@mail.ru	2016-12-26	\N	/images/useravatars/unknownuser/unknownuser.png
76	3	\N	\N	42767516990368493138776584305024125808	\N	76584@mail.ru	2017-02-15	\N	/images/useravatars/unknownuser/unknownuser.png
72	3	\N	\N	42767516990368493138776584305024125808	\N	Haba321@mail.com	2017-02-14	\N	/images/useravatars/unknownuser/unknownuser.png
35	3	Ivan	Alekseev	172615877915217560892406575915100721237	\N	jegius@gmail.com	2017-01-16	\N	/images/useravatars/unknownuser/unknownuser.png
2	2	sergey	sergey	38559796714846015341288105611672564112	222222	manager@manager.com	2016-12-05	6816810077	/images/useravatars/unknownuser/unknownuser.png
70	3	\N	\N	42767516990368493138776584305024125808	\N	Haba@mail.com	2017-02-13	\N	/images/useravatars/unknownuser/unknownuser.png
77	3	\N	\N	42767516990368493138776584305024125808	\N	987@mail.ru	2017-02-15	\N	/images/useravatars/unknownuser/unknownuser.png
78	3	Alexf	Mikles	172615877915217560892406575915100721237	\N	admin@gmail.comd	2017-02-21	\N	/images/useravatars/unknownuser/unknownuser.png
79	3	Alex	Syrus	172615877915217560892406575915100721237	\N	lordever@mail.ru	2017-02-25	\N	/images/useravatars/unknownuser/unknownuser.png
\.


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ecommerceDev1
--

SELECT pg_catalog.setval('users_user_id_seq', 79, true);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id);


--
-- Name: characteristic_groups characteristic_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristic_groups
    ADD CONSTRAINT characteristic_groups_pkey PRIMARY KEY (characteristic_group_id);


--
-- Name: characteristic_values characteristic_values_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristic_values
    ADD CONSTRAINT characteristic_values_pkey PRIMARY KEY (characteristic_id, product_id);


--
-- Name: characteristics characteristics_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristics
    ADD CONSTRAINT characteristics_pkey PRIMARY KEY (characteristic_id);


--
-- Name: discount discount_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY discount
    ADD CONSTRAINT discount_pkey PRIMARY KEY (discount_id);


--
-- Name: order_statuses order_statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY order_statuses
    ADD CONSTRAINT order_statuses_pkey PRIMARY KEY (order_status_id);


--
-- Name: order_items ored_items_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT ored_items_pkey PRIMARY KEY (product_id, sales_order_id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- Name: properties properties_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY properties
    ADD CONSTRAINT properties_pkey PRIMARY KEY (property_id);


--
-- Name: recommended_products recommended_products_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY recommended_products
    ADD CONSTRAINT recommended_products_pkey PRIMARY KEY (source_product_id, target_product_id);


--
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (product_id, user_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- Name: sales_orders sales_orders_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY sales_orders
    ADD CONSTRAINT sales_orders_pkey PRIMARY KEY (sales_order_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: characteristic_values 	FK_characteristic_values_characteristic; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristic_values
    ADD CONSTRAINT "	FK_characteristic_values_characteristic" FOREIGN KEY (characteristic_id) REFERENCES characteristics(characteristic_id);


--
-- Name: characteristic_values 	FK_characteristic_values_products; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristic_values
    ADD CONSTRAINT "	FK_characteristic_values_products" FOREIGN KEY (product_id) REFERENCES products(product_id);


--
-- Name: order_items 	FK_order_items_products; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT "	FK_order_items_products" FOREIGN KEY (product_id) REFERENCES products(product_id);


--
-- Name: products 	FK_products_discount; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY products
    ADD CONSTRAINT "	FK_products_discount" FOREIGN KEY (discount_id) REFERENCES discount(discount_id);


--
-- Name: reviews 	FK_reviews_products; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT "	FK_reviews_products" FOREIGN KEY (product_id) REFERENCES products(product_id);


--
-- Name: reviews 	FK_reviews_user; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT "	FK_reviews_user" FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- Name: sales_orders 	FK_sales_orders_order_statuses; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY sales_orders
    ADD CONSTRAINT "	FK_sales_orders_order_statuses" FOREIGN KEY (order_status_id) REFERENCES order_statuses(order_status_id);


--
-- Name: sales_orders 	FK_sales_orders_users; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY sales_orders
    ADD CONSTRAINT "	FK_sales_orders_users" FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- Name: users 	FK_users_rouls; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "	FK_users_rouls" FOREIGN KEY (role_id) REFERENCES roles(role_id);


--
-- Name: characteristics FK_characteristic_categories; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristics
    ADD CONSTRAINT "FK_characteristic_categories" FOREIGN KEY (category_id) REFERENCES categories(category_id);


--
-- Name: characteristics FK_characteristic_characteristic_group; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY characteristics
    ADD CONSTRAINT "FK_characteristic_characteristic_group" FOREIGN KEY (characteristic_group_id) REFERENCES characteristic_groups(characteristic_group_id);


--
-- Name: order_items FK_order_items_sales_orders; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT "FK_order_items_sales_orders" FOREIGN KEY (sales_order_id) REFERENCES sales_orders(sales_order_id);


--
-- Name: products FK_products_categories; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY products
    ADD CONSTRAINT "FK_products_categories" FOREIGN KEY (category_id) REFERENCES categories(category_id);


--
-- Name: recommended_products recommended_products_source_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY recommended_products
    ADD CONSTRAINT recommended_products_source_product_id_fkey FOREIGN KEY (source_product_id) REFERENCES products(product_id);


--
-- Name: recommended_products recommended_products_target_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ecommerceDev1
--

ALTER TABLE ONLY recommended_products
    ADD CONSTRAINT recommended_products_target_product_id_fkey FOREIGN KEY (target_product_id) REFERENCES products(product_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: ecommerceDev1
--

-- REVOKE ALL ON SCHEMA public FROM rdsadmin;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
-- GRANT ALL ON SCHEMA public TO "ecommerceDev1";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

