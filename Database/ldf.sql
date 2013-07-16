--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.2
-- Started on 2013-07-16 15:06:53 EST

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 16394)
-- Name: ldf; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA ldf;


ALTER SCHEMA ldf OWNER TO postgres;

--
-- TOC entry 178 (class 3079 OID 11995)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 178
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = ldf, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 16395)
-- Name: chart; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE chart (
    idchart integer NOT NULL,
    idfeed integer,
    type character varying(45),
    name character varying(45),
    uriname character varying(45),
    shortname character varying(45),
    xminv integer,
    xmaxv integer,
    "time" character varying(45),
    maxtime character varying(45),
    mintime character varying(45),
    minxaxis integer,
    numvalues integer,
    feedcolumn integer,
    value character varying(100)
);


ALTER TABLE ldf.chart OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16398)
-- Name: feed; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE feed (
    idfeed integer NOT NULL,
    idproject integer NOT NULL,
    type character varying(45),
    name character varying(45),
    numcolumns integer,
    startrow integer,
    numrows integer,
    headerrows integer,
    idlocation integer NOT NULL,
    datecolumn integer,
    timeformat character varying(50),
    timecolumn integer,
    dateformat character varying(50)
);


ALTER TABLE ldf.feed OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16401)
-- Name: gauge; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE gauge (
    idgauge integer NOT NULL,
    idfeed integer,
    type character varying(45),
    name character varying(45),
    uriname character varying(45),
    shortname character varying(45),
    angle character varying(45),
    defaultvalue integer
);


ALTER TABLE ldf.gauge OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16404)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: ldf; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ldf.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16406)
-- Name: location; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE location (
    idlocation integer NOT NULL,
    name character varying(45),
    type character varying(45),
    uri character varying(45),
    baseuri character varying(1000)
);


ALTER TABLE ldf.location OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16412)
-- Name: meter; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE meter (
    idmeter integer NOT NULL,
    idfeed integer,
    type character varying(45),
    name character varying(45),
    uriname character varying(45),
    shortname character varying(45),
    max integer,
    min integer,
    defaultvale integer
);


ALTER TABLE ldf.meter OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16415)
-- Name: project; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE project (
    idproject integer NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    name character varying(45) NOT NULL,
    details character varying(500)
);


ALTER TABLE ldf.project OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16421)
-- Name: script; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE script (
    idscript integer NOT NULL,
    scriptloc character varying(400),
    inputfile character varying(400),
    outputfile character varying(400),
    options character varying(200),
    status boolean
);


ALTER TABLE ldf.script OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16427)
-- Name: user; Type: TABLE; Schema: ldf; Owner: postgres; Tablespace: 
--

CREATE TABLE "user" (
    iduser integer NOT NULL,
    name character varying(45),
    email character varying(45),
    type character varying(45),
    ownsproject character varying(45)
);


ALTER TABLE ldf."user" OWNER TO postgres;

--
-- TOC entry 2235 (class 0 OID 16395)
-- Dependencies: 169
-- Data for Name: chart; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY chart (idchart, idfeed, type, name, uriname, shortname, xminv, xmaxv, "time", maxtime, mintime, minxaxis, numvalues, feedcolumn, value) FROM stdin;
47	25	image	\N	temperature.Chart.png	Temperature	0	0				0	0	0	\N
53	52	csv	\N	argonaut-u.csv	cero	0	0				0	50	3	\N
54	2	csv	\N	argonaut-c.csv	Argon-temp	0	0				0	0	4	\N
58	2	csv	\N	argonaut-c.csv	Argon-Flow	0	0				0	0	12	\N
55	2	csv	\N	argonaut-c.csv	argon-Level	0	0				0	0	6	\N
67	25	image	\N	temperature.Chart.png	date	0	0				0	0	0	\N
56	2	csv	\N	argonaut-c.csv	Argon-CellEnd	0	0		30		0	120	5	\N
57	2	csv	\N	argonaut-c.csv	Argon-Velocity1	0	0				0	100	8	\N
\.


--
-- TOC entry 2236 (class 0 OID 16398)
-- Dependencies: 170
-- Data for Name: feed; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY feed (idfeed, idproject, type, name, numcolumns, startrow, numrows, headerrows, idlocation, datecolumn, timeformat, timecolumn, dateformat) FROM stdin;
52	51	js	cero	9	2	50	1	30	0	yyyy-MM-dd hh:mm:ss	1	\N
66	7	Soil		0	0	0	0	30	0		0	
2	7	Weather	Argon	12	2	30	1	30	1	hh:mm:ss a	2	yyyy-mm-dd
68	9	soil	soil	0	0	0	0	35	0		0	
69	9	flux	flux	0	0	0	0	35	0		0	
25	9	weather	weather	0	0	0	0	35	0		0	
\.


--
-- TOC entry 2237 (class 0 OID 16401)
-- Dependencies: 171
-- Data for Name: gauge; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY gauge (idgauge, idfeed, type, name, uriname, shortname, angle, defaultvalue) FROM stdin;
\.


--
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 172
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: ldf; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 69, true);


--
-- TOC entry 2239 (class 0 OID 16406)
-- Dependencies: 173
-- Data for Name: location; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY location (idlocation, name, type, uri, baseuri) FROM stdin;
35	Tumbarumba	images		/other/tumbarumba/
30	SERF	location2		/other/serf/
\.


--
-- TOC entry 2240 (class 0 OID 16412)
-- Dependencies: 174
-- Data for Name: meter; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY meter (idmeter, idfeed, type, name, uriname, shortname, max, min, defaultvale) FROM stdin;
\.


--
-- TOC entry 2241 (class 0 OID 16415)
-- Dependencies: 175
-- Data for Name: project; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY project (idproject, latitude, longitude, name, details) FROM stdin;
9	-35.6481950000000012	148.141365000000008	Tumbarumba	none
7	-27.3887819999999991	152.871987999999988	SERF	details
51	0	0	cero	
\.


--
-- TOC entry 2242 (class 0 OID 16421)
-- Dependencies: 176
-- Data for Name: script; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY script (idscript, scriptloc, inputfile, outputfile, options, status) FROM stdin;
1	/Users/Moy/Desktop/pre_process_ver5.r 	/Users/Moy/Desktop/argonaut-real.csv 	/Users/Moy/Desktop/converted-Argon.csv	Argon	f
63	/Users/sacalbon/Dropbox/SERF/pre_process_ver5.r 	/Users/sacalbon/Dropbox/SERF/TEST/raw.txt	/Users/sacalbon/java/ldf8/DataFeeds/src/main/webapp/WEB-INF/other/serf/raw-a.csv	Argon	f
3	/Users/sacalbon/Dropbox/SERF/pre_process_ver7.r	/Users/sacalbon/Dropbox/SERF/pumpshed_TenMinute.dat	/Users/sacalbon/java/ldf8/DataFeeds/src/main/webapp/WEB-INF/other/serf/argonaut-c.csv	Argon	t
\.


--
-- TOC entry 2243 (class 0 OID 16427)
-- Dependencies: 177
-- Data for Name: user; Type: TABLE DATA; Schema: ldf; Owner: postgres
--

COPY "user" (iduser, name, email, type, ownsproject) FROM stdin;
\.


--
-- TOC entry 2215 (class 2606 OID 16431)
-- Name: chart_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY chart
    ADD CONSTRAINT chart_pkey PRIMARY KEY (idchart);


--
-- TOC entry 2217 (class 2606 OID 16433)
-- Name: feed_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY feed
    ADD CONSTRAINT feed_pkey PRIMARY KEY (idfeed);


--
-- TOC entry 2219 (class 2606 OID 16435)
-- Name: gauge_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gauge
    ADD CONSTRAINT gauge_pkey PRIMARY KEY (idgauge);


--
-- TOC entry 2221 (class 2606 OID 16437)
-- Name: location_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY location
    ADD CONSTRAINT location_pkey PRIMARY KEY (idlocation);


--
-- TOC entry 2223 (class 2606 OID 16439)
-- Name: meter_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY meter
    ADD CONSTRAINT meter_pkey PRIMARY KEY (idmeter);


--
-- TOC entry 2225 (class 2606 OID 16441)
-- Name: project_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY project
    ADD CONSTRAINT project_pkey PRIMARY KEY (idproject);


--
-- TOC entry 2227 (class 2606 OID 16443)
-- Name: script_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY script
    ADD CONSTRAINT script_pkey PRIMARY KEY (idscript);


--
-- TOC entry 2229 (class 2606 OID 16445)
-- Name: user_pkey; Type: CONSTRAINT; Schema: ldf; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (iduser);


--
-- TOC entry 2233 (class 2606 OID 16446)
-- Name: idfeed; Type: FK CONSTRAINT; Schema: ldf; Owner: postgres
--

ALTER TABLE ONLY gauge
    ADD CONSTRAINT idfeed FOREIGN KEY (idfeed) REFERENCES feed(idfeed);


--
-- TOC entry 2234 (class 2606 OID 16451)
-- Name: idfeed; Type: FK CONSTRAINT; Schema: ldf; Owner: postgres
--

ALTER TABLE ONLY meter
    ADD CONSTRAINT idfeed FOREIGN KEY (idfeed) REFERENCES feed(idfeed);


--
-- TOC entry 2230 (class 2606 OID 16456)
-- Name: idfeed; Type: FK CONSTRAINT; Schema: ldf; Owner: postgres
--

ALTER TABLE ONLY chart
    ADD CONSTRAINT idfeed FOREIGN KEY (idfeed) REFERENCES feed(idfeed);


--
-- TOC entry 2231 (class 2606 OID 16461)
-- Name: idlocation; Type: FK CONSTRAINT; Schema: ldf; Owner: postgres
--

ALTER TABLE ONLY feed
    ADD CONSTRAINT idlocation FOREIGN KEY (idlocation) REFERENCES location(idlocation);


--
-- TOC entry 2232 (class 2606 OID 16466)
-- Name: idproject; Type: FK CONSTRAINT; Schema: ldf; Owner: postgres
--

ALTER TABLE ONLY feed
    ADD CONSTRAINT idproject FOREIGN KEY (idproject) REFERENCES project(idproject);


--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-07-16 15:06:54 EST

--
-- PostgreSQL database dump complete
--

