-- INSERT DE ESTUDIOS
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('Studio Pierrot', 'Japón', '1979-05-01', true);
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('Toei Animation', 'Japón', '1948-01-01', true);
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('Madhouse', 'Japón', '1972-10-17', true);
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('Bones', 'Japón', '1998-10-01', true);
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('Ufotable', 'Japón', '2000-10-01', true);
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('MAPPA', 'Japón', '2011-06-14', true);
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('Wit Studio', 'Japón', '2012-06-01', true);
INSERT INTO estudios (nombre, pais, fecha_creacion, activo) VALUES ('Kyoto Animation', 'Japón', '1981-07-12', true);

-- INSERT DE CATEGORIAS
INSERT INTO categorias (nombre, activo) VALUES ('Shonen', true);
INSERT INTO categorias (nombre, activo) VALUES ('Seinen', true);
INSERT INTO categorias (nombre, activo) VALUES ('Shojo', true);
INSERT INTO categorias (nombre, activo) VALUES ('Isekai', true);
INSERT INTO categorias (nombre, activo) VALUES ('Mecha', true);
INSERT INTO categorias (nombre, activo) VALUES ('Slice of Life', true);
INSERT INTO categorias (nombre, activo) VALUES ('Deportes', true);
INSERT INTO categorias (nombre, activo) VALUES ('Comedia', true);

-- INSERT DE ANIMES (Usando los IDs asumiendo que H2 los generó en orden del 1 al 8)
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Naruto', 220, 'FINALIZADO', '2002-10-03', '2007-02-08', 1, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Naruto Shippuden', 500, 'FINALIZADO', '2007-02-15', '2017-03-23', 1, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('One Piece', 1100, 'EN_EMISION', '1999-10-20', NULL, 2, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Dragon Ball Z', 291, 'FINALIZADO', '1989-04-26', '1996-01-31', 2, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Death Note', 37, 'FINALIZADO', '2006-10-03', '2007-06-26', 3, 2, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Hunter x Hunter', 148, 'FINALIZADO', '2011-10-02', '2014-09-24', 3, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Fullmetal Alchemist: Brotherhood', 64, 'FINALIZADO', '2009-04-05', '2010-07-04', 4, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('My Hero Academia', 138, 'EN_EMISION', '2016-04-03', NULL, 4, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Demon Slayer', 55, 'EN_EMISION', '2019-04-06', NULL, 5, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Attack on Titan', 87, 'FINALIZADO', '2013-04-07', '2023-11-04', 6, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Jujutsu Kaisen', 47, 'EN_EMISION', '2020-10-03', NULL, 6, 1, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Spy x Family', 37, 'EN_EMISION', '2022-04-09', NULL, 7, 6, true);
INSERT INTO animes (nombre, capitulos, estado, primera_emision, ultima_emision, estudio_id, categoria_id, activo) VALUES ('Violet Evergarden', 13, 'FINALIZADO', '2018-01-11', '2018-04-05', 8, 6, true);