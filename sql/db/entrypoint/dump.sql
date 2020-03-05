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
    vorname character varying(255)
);


ALTER TABLE public.bewerber OWNER TO trapper;

--
-- Data for Name: bewerber; Type: TABLE DATA; Schema: public; Owner: trapper
--

COPY public.bewerber (kennung, geburtsdatum, nachname, vorname) FROM stdin;
emjo100	23.04.2000	Johnson	Emma
uljo234	01.02.1999	Johanson	Ulrich
mahi932	28.02.1997	Hills	Mark
bewa232	12.04.1998	Watson	Benjamin
gewa101	09.11.1998	George	Walter
frabu102	13.04.1986	Burke	Frank
pabar100	15.02.1987	Barber	Paula
jokin238	21.12.1988	King	Johan
alkem472	25.03.1997	Kemp	Alida
guful312	09.11.1997	Fuller	Gunnel
mefle224	26.09.1988	Fleming	Medwin
togil111	14.11.1989	Gilbert	Torn
hecoh443	30.09.1990	Cohen	Helene
brbol435	24.02.1992	Bolt	Brandeis
vecha333	27.01.1996	Chapman	Vera
\.


--
-- Name: bewerber bewerber_pkey; Type: CONSTRAINT; Schema: public; Owner: trapper
--

ALTER TABLE ONLY public.bewerber
    ADD CONSTRAINT bewerber_pkey PRIMARY KEY (kennung);


--
-- PostgreSQL database dump complete
--

