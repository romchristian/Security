INSERT INTO usuario (id, estado, fecharegistro, fechaultimamodificacion, nombre, password, username) VALUES (1, 'ACTIVO', NULL, NULL, 'Roberto Riquelme', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'admin');
INSERT INTO usuario (id, estado, fecharegistro, fechaultimamodificacion, nombre, password, username) VALUES (2, 'ACTIVO', NULL, NULL, 'Invitado', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'guest');


SELECT pg_catalog.setval('usuario_id_seq', 2, true);

--
-- TOC entry 2304 (class 0 OID 26953)
-- Dependencies: 206
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO empresa (id, contactociudad, contactodireccion, contactoemail, contactopais, contactotelefono, contactoweb, esretentor, estado, fecharegistro, fechaultimamodificacion, nombre, nombrerepresentantelegal, permitiroperaciondesde, retencionmontominimo, ruc, rucrepresentantelegal, seleccioncentroscostosencompras, sumarinteresalprecio, tasaanualinteres, tasamensualinteres, tasaretencioniva, tasaretencionrenta,verificarlineacreditocliente, usuarioultimamodificacion) VALUES (1, NULL, 'España', NULL, NULL, 'sdasd', NULL, false, 'ACTIVO', '2016-03-23 17:52:35.736', NULL, 'Consultorio Roberto', NULL, NULL, NULL, '1234564', NULL, false, false, NULL, NULL, NULL, NULL, false, NULL);




INSERT INTO modulo (id, defaultpage, estado, fecharegistro, fechaultimamodificacion, iconfont, nombre, subtitle, usuarioultimamodificacion) VALUES (1, '/main/consultorio/home.xhtml?idMenu=1', 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-printer2 Opac80 Fs22', 'Consultorio', 'Fichas..', NULL);



--
-- TOC entry 2300 (class 0 OID 26931)
-- Dependencies: 202
-- Data for Name: submenu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO submenu (id, estado, fecharegistro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (1, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Configuraciones', 1, NULL);
INSERT INTO submenu (id, estado, fecharegistro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (2, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Seguridad', 1, NULL);
INSERT INTO submenu (id, estado, fecharegistro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (3, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Informes', 1, NULL);
INSERT INTO submenu (id, estado, fecharegistro, fechaultimamodificacion, icon, label, modulo_id, usuarioultimamodificacion) VALUES (4, 'ACTIVO', '2016-03-18 17:35:15', NULL, 'icon-uniE675', 'Principal', 1, NULL);


--
-- TOC entry 2282 (class 0 OID 26832)
-- Dependencies: 184
-- Data for Name: menuitem; Type: TABLE DATA; Schema: public; Owner: postgres
--


INSERT INTO menuitem (id, estado, fecharegistro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion,disabled) VALUES (1, 'ACTIVO', NULL, NULL, 'icon-tags', '/main/consultorio/configuracion/empresa/listado.xhtml', 'Empresa', 1, NULL,false);
INSERT INTO menuitem (id, estado, fecharegistro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion,disabled) VALUES (2, 'ACTIVO', NULL, NULL, 'icon-tags', '/main/seguridad/rol/listado.xhtml', 'Roles', 2, NULL,false);
INSERT INTO menuitem (id, estado, fecharegistro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion,disabled) VALUES (3, 'ACTIVO', NULL, NULL, 'icon-tags', '/main/seguridad/usuario/listado.xhtml', 'Usuarios', 2, NULL,false);

INSERT INTO menuitem (id, estado, fecharegistro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion,disabled) VALUES (4, 'ACTIVO', NULL, NULL, 'icon-tags', '/main/consultorio/paciente/listado.xhtml', 'Pacientes', 4, NULL,false);
INSERT INTO menuitem (id, estado, fecharegistro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion,disabled) VALUES (5, 'ACTIVO', NULL, NULL, 'icon-tags', '/main/consultorio/tratamiento/listado.xhtml', 'Tratamientos', 4, NULL,false);
INSERT INTO menuitem (id, estado, fecharegistro, fechaultimamodificacion, icon, url, valor, submenu_id, usuarioultimamodificacion,disabled) VALUES (6, 'ACTIVO', NULL, NULL, 'icon-tags', '/main/consultorio/consulta/listado.xhtml', 'Consultas', 4, NULL,false);


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 183
-- Name: menuitem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menuitem_id_seq', 7, true);


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 199
-- Name: modulo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('modulo_id_seq', 2, true);


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 201
-- Name: submenu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('submenu_id_seq', 4, true);





--
-- TOC entry 2308 (class 0 OID 26978)
-- Dependencies: 210
-- Data for Name: usuario_empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--




INSERT INTO usuario_empresa (empresas_id, usuario_id) VALUES (1, 1);
INSERT INTO usuario_empresa (empresas_id, usuario_id) VALUES (1, 2);


INSERT INTO grupo (nombre, empresa_id, estado) VALUES ('admin',1,'ACTIVO');
INSERT INTO grupo(nombre, empresa_id, estado) VALUES ('consultariofacil',1,'ACTIVO');

INSERT INTO usuario_grupo (grupos_id, usuarios_id) VALUES (1, 1);
INSERT INTO usuario_grupo (grupos_id, usuarios_id) VALUES (2, 2);



CREATE OR REPLACE VIEW public.groups AS 
 SELECT u.username AS userid,
    g.nombre AS groupid
   FROM usuario_grupo ug
     JOIN usuario u ON u.id = ug.usuarios_id
     JOIN grupo g ON g.id = ug.grupos_id
  WHERE u.estado::text = 'ACTIVO'::text;

ALTER TABLE public.groups
  OWNER TO postgres;


  CREATE OR REPLACE VIEW public.users AS 
 SELECT usuario.username AS userid,
    usuario.password
   FROM usuario
  WHERE usuario.estado::text = 'ACTIVO'::text;

ALTER TABLE public.users
  OWNER TO postgres;


