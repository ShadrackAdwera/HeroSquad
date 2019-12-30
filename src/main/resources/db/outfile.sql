--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: hero; Type: TABLE; Schema: public; Owner: adwesh
--

CREATE TABLE public.hero (
    id integer NOT NULL,
    name character varying,
    age integer,
    superpowers character varying,
    weakness character varying,
    squad boolean,
    squadid integer
);


ALTER TABLE public.hero OWNER TO adwesh;

--
-- Name: hero_id_seq; Type: SEQUENCE; Schema: public; Owner: adwesh
--

CREATE SEQUENCE public.hero_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hero_id_seq OWNER TO adwesh;

--
-- Name: hero_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: adwesh
--

ALTER SEQUENCE public.hero_id_seq OWNED BY public.hero.id;


--
-- Name: squads; Type: TABLE; Schema: public; Owner: adwesh
--

CREATE TABLE public.squads (
    id integer NOT NULL,
    name character varying,
    maxsize integer,
    cause character varying
);


ALTER TABLE public.squads OWNER TO adwesh;

--
-- Name: squads_id_seq; Type: SEQUENCE; Schema: public; Owner: adwesh
--

CREATE SEQUENCE public.squads_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.squads_id_seq OWNER TO adwesh;

--
-- Name: squads_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: adwesh
--

ALTER SEQUENCE public.squads_id_seq OWNED BY public.squads.id;


--
-- Name: hero id; Type: DEFAULT; Schema: public; Owner: adwesh
--

ALTER TABLE ONLY public.hero ALTER COLUMN id SET DEFAULT nextval('public.hero_id_seq'::regclass);


--
-- Name: squads id; Type: DEFAULT; Schema: public; Owner: adwesh
--

ALTER TABLE ONLY public.squads ALTER COLUMN id SET DEFAULT nextval('public.squads_id_seq'::regclass);


--
-- Data for Name: hero; Type: TABLE DATA; Schema: public; Owner: adwesh
--

COPY public.hero (id, name, age, superpowers, weakness, squad, squadid) FROM stdin;
23	q	88	Eat food	deeeeeeeeez nuts	\N	63
\.


--
-- Data for Name: squads; Type: TABLE DATA; Schema: public; Owner: adwesh
--

COPY public.squads (id, name, maxsize, cause) FROM stdin;
63	Delani Studio	4	genge
\.


--
-- Name: hero_id_seq; Type: SEQUENCE SET; Schema: public; Owner: adwesh
--

SELECT pg_catalog.setval('public.hero_id_seq', 23, true);


--
-- Name: squads_id_seq; Type: SEQUENCE SET; Schema: public; Owner: adwesh
--

SELECT pg_catalog.setval('public.squads_id_seq', 63, true);


--
-- Name: hero hero_pkey; Type: CONSTRAINT; Schema: public; Owner: adwesh
--

ALTER TABLE ONLY public.hero
    ADD CONSTRAINT hero_pkey PRIMARY KEY (id);


--
-- Name: squads squads_pkey; Type: CONSTRAINT; Schema: public; Owner: adwesh
--

ALTER TABLE ONLY public.squads
    ADD CONSTRAINT squads_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

