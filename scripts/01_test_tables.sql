--
-- PostgreSQL database dump
--
Drop TABLE IF EXISTS categories CASCADE;
Drop TABLE IF EXISTS characteristic_groups CASCADE;
Drop TABLE IF EXISTS characteristic_values CASCADE;
Drop TABLE IF EXISTS characteristics CASCADE;
Drop TABLE IF EXISTS discount CASCADE;
Drop TABLE IF EXISTS order_items CASCADE;
Drop TABLE IF EXISTS order_statuses CASCADE;
Drop TABLE IF EXISTS products CASCADE;
Drop TABLE IF EXISTS properties CASCADE;
Drop TABLE IF EXISTS recommended_products CASCADE;
Drop TABLE IF EXISTS reviews CASCADE;
Drop TABLE IF EXISTS roles CASCADE;
Drop TABLE IF EXISTS sales_orders CASCADE;
Drop TABLE IF EXISTS users CASCADE;

--
-- Name: categories; Type: TABLE; Schema: public
--

CREATE TABLE categories (
    category_id bigint NOT NULL PRIMARY KEY,
    parent_id bigint NULL,
    name character varying(100) NOT NULL,
    description character varying(1000)
);

--
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE categories_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: categories_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE public.categories ALTER COLUMN category_id SET DEFAULT nextval('public.categories_category_id_seq');
ALTER SEQUENCE categories_category_id_seq OWNED BY categories.category_id;

--
-- Name: characteristic_groups; Type: TABLE; Schema: public 
--

CREATE TABLE characteristic_groups (
    characteristic_group_id bigint NOT NULL PRIMARY KEY,
    name character varying(100) NOT NULL
);


--
-- Name: characteristic_groups_characteristic_group_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE characteristic_groups_characteristic_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: characteristic_groups_characteristic_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE characteristic_groups ALTER COLUMN characteristic_group_id SET DEFAULT nextval('characteristic_groups_characteristic_group_id_seq');
ALTER SEQUENCE characteristic_groups_characteristic_group_id_seq OWNED BY characteristic_groups.characteristic_group_id;

--
-- Name: characteristic_values; Type: TABLE; Schema: public 
--

CREATE TABLE characteristic_values (
    characteristic_id bigint NOT NULL,
    product_id bigint NOT NULL,
    value character varying(255) NOT NULL,
	PRIMARY KEY (characteristic_id, product_id)
);

--
-- Name: characteristics; Type: TABLE; Schema: public 
--

CREATE TABLE characteristics (
    characteristic_id bigint NOT NULL PRIMARY KEY,
    category_id bigint NOT NULL,
    name character varying(100) NOT NULL,
    characteristic_group_id bigint NOT NULL
); 

--
-- Name: characteristics_characteristic_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE characteristics_characteristic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1; 

--
-- Name: characteristics_characteristic_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE characteristics ALTER COLUMN characteristic_id SET DEFAULT nextval('characteristics_characteristic_id_seq');
ALTER SEQUENCE characteristics_characteristic_id_seq OWNED BY characteristics.characteristic_id;


--
-- Name: discount; Type: TABLE; Schema: public 
--

CREATE TABLE discount (
    discount_id bigint NOT NULL PRIMARY KEY,
    name character varying(100) NOT NULL,
    value smallint NOT NULL
);

--
-- Name: discount_discount_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE discount_discount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: discount_discount_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE discount ALTER COLUMN discount_id SET DEFAULT nextval('discount_discount_id_seq');
ALTER SEQUENCE discount_discount_id_seq OWNED BY discount.discount_id;


--
-- Name: order_items; Type: TABLE; Schema: public 
--

CREATE TABLE order_items (
    product_id bigint NOT NULL,
    sales_order_id bigint NOT NULL,
    quantity integer NOT NULL,
	PRIMARY KEY (product_id, sales_order_id)
);
 

--
-- Name: order_statuses; Type: TABLE; Schema: public 
--

CREATE TABLE order_statuses (
    order_status_id bigint NOT NULL PRIMARY KEY,
    name character varying(100) NOT NULL
);

--
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE order_statuses_order_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--

ALTER TABLE order_statuses ALTER COLUMN order_status_id SET DEFAULT nextval('order_statuses_order_status_id_seq');
ALTER SEQUENCE order_statuses_order_status_id_seq OWNED BY order_statuses.order_status_id;

--
-- Name: products; Type: TABLE; Schema: public 
--

CREATE TABLE products (
    product_id bigint NOT NULL PRIMARY KEY,
    category_id bigint NOT NULL,
    name character varying(250) NOT NULL,
    description character varying(1000) NOT NULL,
    discount_id smallint,
    price numeric(12,2) NOT NULL
);

--
-- Name: products_product_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE products_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: products_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE products ALTER COLUMN product_id SET DEFAULT nextval('products_product_id_seq');
ALTER SEQUENCE products_product_id_seq OWNED BY products.product_id;

--
-- Name: properties; Type: TABLE; Schema: public 
--

CREATE TABLE properties (
    property_id character varying(100) NOT NULL PRIMARY KEY,
    value character varying(500) NOT NULL
); 

--
-- Name: recommended_products; Type: TABLE; Schema: public 
--

CREATE TABLE recommended_products (
    source_product_id bigint NOT NULL,
    target_product_id bigint NOT NULL,
	PRIMARY KEY (source_product_id, target_product_id)
	
);

--
-- Name: reviews; Type: TABLE; Schema: public 
--

CREATE TABLE reviews (
    product_id bigint NOT NULL,
    user_id bigint NOT NULL,
    description character varying(3000) NOT NULL,
    creation_date date NOT NULL,
    raiting smallint NOT NULL,
	PRIMARY KEY (product_id, user_id),
    CONSTRAINT raiting_check_for_ten CHECK (((raiting >= 0) AND (raiting <= 10)))
);

--
-- Name: roles; Type: TABLE; Schema: public 
--

CREATE TABLE roles (
    role_id bigint NOT NULL PRIMARY KEY,
    name character varying(100) NOT NULL
);


--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE roles ALTER COLUMN role_id SET DEFAULT nextval('roles_role_id_seq');
ALTER SEQUENCE roles_role_id_seq OWNED BY roles.role_id;

--
-- Name: sales_orders; Type: TABLE; Schema: public 
--

CREATE TABLE sales_orders (
    sales_order_id bigint NOT NULL PRIMARY KEY,
    user_id bigint NOT NULL,
    creation_date date NOT NULL,
    "limit" money NOT NULL,
    order_status_id bigint NOT NULL
);

--
-- Name: sales_orders_sales_order_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE sales_orders_sales_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: sales_orders_sales_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE sales_orders ALTER COLUMN sales_order_id SET DEFAULT nextval('sales_orders_sales_order_id_seq');
ALTER SEQUENCE sales_orders_sales_order_id_seq OWNED BY sales_orders.sales_order_id;

--
-- Name: users; Type: TABLE; Schema: public 
--

CREATE TABLE users (
    user_id bigint NOT NULL PRIMARY KEY,
    role_id bigint NOT NULL,
    login character varying(100) NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    password character varying(1000) NOT NULL,
    phone character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    registration_date date NOT NULL
);

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public 
--

CREATE SEQUENCE users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public 
--
ALTER TABLE users ALTER COLUMN user_id SET DEFAULT nextval('users_user_id_seq');

ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;

--
-- Name: characteristic_values 	FK_characteristic_values_characteristic; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY characteristic_values
    ADD CONSTRAINT "	FK_characteristic_values_characteristic" FOREIGN KEY (characteristic_id) REFERENCES characteristics(characteristic_id);

--
-- Name: characteristic_values 	FK_characteristic_values_products; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY characteristic_values
    ADD CONSTRAINT "	FK_characteristic_values_products" FOREIGN KEY (product_id) REFERENCES products(product_id);

--
-- Name: order_items 	FK_order_items_products; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT "	FK_order_items_products" FOREIGN KEY (product_id) REFERENCES products(product_id);

--
-- Name: products 	FK_products_discount; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT "	FK_products_discount" FOREIGN KEY (discount_id) REFERENCES discount(discount_id);


--
-- Name: reviews 	FK_reviews_products; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT "	FK_reviews_products" FOREIGN KEY (product_id) REFERENCES products(product_id);


--
-- Name: reviews 	FK_reviews_user; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT "	FK_reviews_user" FOREIGN KEY (user_id) REFERENCES users(user_id);

--
-- Name: sales_orders 	FK_sales_orders_order_statuses; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY sales_orders
    ADD CONSTRAINT "	FK_sales_orders_order_statuses" FOREIGN KEY (order_status_id) REFERENCES order_statuses(order_status_id);

--
-- Name: sales_orders 	FK_sales_orders_users; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY sales_orders
    ADD CONSTRAINT "	FK_sales_orders_users" FOREIGN KEY (user_id) REFERENCES users(user_id);

--
-- Name: users 	FK_users_rouls; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "	FK_users_rouls" FOREIGN KEY (role_id) REFERENCES roles(role_id);

--
-- Name: characteristics FK_characteristic_categories; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY characteristics
    ADD CONSTRAINT "FK_characteristic_categories" FOREIGN KEY (category_id) REFERENCES categories(category_id);

--
-- Name: characteristics FK_characteristic_characteristic_group; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY characteristics
    ADD CONSTRAINT "FK_characteristic_characteristic_group" FOREIGN KEY (characteristic_group_id) REFERENCES characteristic_groups(characteristic_group_id);

--
-- Name: order_items FK_order_items_sales_orders; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY order_items
    ADD CONSTRAINT "FK_order_items_sales_orders" FOREIGN KEY (sales_order_id) REFERENCES sales_orders(sales_order_id);

--
-- Name: products FK_products_categories; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT "FK_products_categories" FOREIGN KEY (category_id) REFERENCES categories(category_id);

--
-- Name: recommended_products recommended_products_source_product_id_fkey; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY recommended_products
    ADD CONSTRAINT recommended_products_source_product_id_fkey FOREIGN KEY (source_product_id) REFERENCES products(product_id);

--
-- Name: recommended_products recommended_products_target_product_id_fkey; Type: FK CONSTRAINT; Schema: public 
--

ALTER TABLE ONLY recommended_products
    ADD CONSTRAINT recommended_products_target_product_id_fkey FOREIGN KEY (target_product_id) REFERENCES products(product_id);
