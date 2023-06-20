--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases (except postgres and template1)
--

DROP DATABASE "trainingsDB";




--
-- Drop roles
--

DROP ROLE postgres;


--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:kANlr369AcBbgZghwBO2dA==$9zybt7lqr6fkOY3Uup8avY/dtt+RsDmykp+ArJ/HHpo=:H/YMbQZRNUnIwMaZn9Q/nw45lT8YYj+Rej2XamxVK5A=';






--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO postgres;

\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: postgres
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: postgres
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- PostgreSQL database dump complete
--

--
-- Database "trainingsDB" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: trainingsDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "trainingsDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE "trainingsDB" OWNER TO postgres;

\connect "trainingsDB"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.training (
    end_date date,
    price numeric(38,2),
    start_date date,
    id bigint NOT NULL,
    description character varying(255),
    lecturer character varying(255),
    name character varying(255),
    booked_customer character varying(255)[]
);


ALTER TABLE public.training OWNER TO postgres;

--
-- Name: training_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.training_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.training_id_seq OWNER TO postgres;

--
-- Name: training_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.training_id_seq OWNED BY public.training.id;


--
-- Name: training id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training ALTER COLUMN id SET DEFAULT nextval('public.training_id_seq'::regclass);


--
-- Data for Name: training; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.training (end_date, price, start_date, id, description, lecturer, name, booked_customer) FROM stdin;
2023-08-22	1099.00	2023-07-23	1	Create strategy and execute online campaigns etc.	Adam Stavanger	Social Network Management	{}
2023-04-29	899.00	2023-04-01	2	Get tips for interviews	Tiffany Uman	Nano Tips for Answering Common Interview Questions	{}
2023-09-22	699.00	2023-05-23	3	Microsoft 365	Dennis Taylor	Excel Essential Training (Microsoft 365)	{}
2023-02-04	1299.00	2023-01-05	4	OpenAI ChatGPT applications in real-world	Rachel Woods	ChatGPT for Businesses	{}
2023-11-09	999.00	2023-10-10	5	Get the top-notch tips to build your brand	Shade Zaharai	Building a Super Star Brand	{}
2024-01-22	1599.00	2023-12-23	6	Manager of future	Sho Dewan	Be the Manager People Look Up To	{}
2023-10-17	1699.00	2023-09-18	7	PM for beginners	Curt Frye	Project Management Foundations	{}
2023-09-22	1099.00	2023-08-23	8	Create strategy and execute online campaigns etc.	Adam Stavanger	Social Network Management	{}
2023-05-30	899.00	2023-05-01	9	Get tips for interviews	Tiffany Uman	Nano Tips for Answering Common Interview Questions	{}
2023-07-22	699.00	2023-06-23	10	Microsoft 365	Dennis Taylor	Excel Essential Training (Microsoft 365)	{}
2023-03-04	1299.00	2023-02-05	11	OpenAI ChatGPT applications in real-world	Rachel Woods	ChatGPT for Businesses	{}
2023-12-09	999.00	2023-11-10	12	Get the top-notch tips to build your brand	Shade Zaharai	Building a Super Star Brand	{}
2024-12-22	1599.00	2023-11-23	13	Manager of future	Sho Dewan	Be the Manager People Look Up To	{}
2023-11-17	1699.00	2023-10-18	14	PM for beginners	Curt Frye	Project Management Foundations	{}
2023-10-22	1099.00	2023-09-23	15	Create strategy and execute online campaigns etc.	Adam Stavanger	Social Network Management	{}
2023-06-30	899.00	2023-06-01	16	Get tips for interviews	Tiffany Uman	Nano Tips for Answering Common Interview Questions	{}
2023-08-22	699.00	2023-07-23	17	Microsoft 365	Dennis Taylor	Excel Essential Training (Microsoft 365)	{}
2023-04-04	1299.00	2023-03-05	18	OpenAI ChatGPT applications in real-world	Rachel Woods	ChatGPT for Businesses	{}
2024-01-09	999.00	2023-12-10	19	Get the top-notch tips to build your brand	Shade Zaharai	Building a Super Star Brand	{}
2024-11-22	1599.00	2023-10-23	20	Manager of future	Sho Dewan	Be the Manager People Look Up To	{}
2023-12-17	1699.00	2023-11-18	21	PM for beginners	Curt Frye	Project Management Foundations	{}
\.


--
-- Name: training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.training_id_seq', 1, true);


--
-- Name: training training_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training
    ADD CONSTRAINT training_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

