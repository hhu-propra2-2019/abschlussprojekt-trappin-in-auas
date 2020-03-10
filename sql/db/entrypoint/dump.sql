 
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Debian 12.2-2.pgdg100+1)

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
-- Name: bewerber; Type: TABLE; Schema: public; Owner: trapper
--

CREATE TABLE public.bewerber (
    kennung character varying(255) NOT NULL,
    geburtsdatum character varying(255),
    nachname character varying(255),
    vorname character varying(255),
    verteilt_an character varying
);


ALTER TABLE public.bewerber OWNER TO trapper;

--
-- Data for Name: bewerber; Type: TABLE DATA; Schema: public; Owner: trapper
--

COPY public.bewerber (kennung, geburtsdatum, nachname, vorname, verteilt_an) FROM stdin;
emjo100	23.04.2000	Johnson	Emma	\N
uljo234	01.02.1999	Johanson	Ulrich	\N
mahi932	28.02.1997	Hills	Mark	\N
bewa232	12.04.1998	Watson	Benjamin	\N
gewa101	09.11.1998	George	Walter	\N
frabu102	13.04.1986	Burke	Frank	\N
pabar100	15.02.1987	Barber	Paula	\N
jokin238	21.12.1988	King	Johan	\N
alkem472	25.03.1997	Kemp	Alida	\N
guful312	09.11.1997	Fuller	Gunnel	\N
mefle224	26.09.1988	Fleming	Medwin	\N
togil111	14.11.1989	Gilbert	Torn	\N
hecoh443	30.09.1990	Cohen	Helene	\N
brbol435	24.02.1992	Bolt	Brandeis	\N
vecha333	27.01.1996	Chapman	Vera	\N
asd122	29.03.1999	b	alan	\N
\.


--
-- Name: bewerber bewerber_pkey; Type: CONSTRAINT; Schema: public; Owner: trapper
--

ALTER TABLE ONLY public.bewerber
    ADD CONSTRAINT bewerber_pkey PRIMARY KEY (kennung);



