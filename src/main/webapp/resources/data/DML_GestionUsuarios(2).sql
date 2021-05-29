
insert into usuario values (SQUSUARIO.nextval, 'admin', (select upper(md5hash('admin')) from dual), 0);
commit;

INSERT INTO ESTADOUSUARIO values (SQESTADOUSUARIO.NEXTVAL, 
(SELECT pk_Usuario FROM USUARIO WHERE nombreusuario = 'admin'), 
(SELECT pk_ESTADO FROM ESTADO WHERE CODIGO = '1'),
sysdate, sysdate);

commit;

insert into usuario values (SQUSUARIO.nextval, null, 'cmaya', (select upper(md5hash('cmaya')) from dual), 1);

commit;

INSERT INTO ESTADOUSUARIO values (SQESTADOUSUARIO.NEXTVAL, 
(SELECT pk_Usuario FROM USUARIO WHERE nombreusuario = 'cmaya'), 
(SELECT pk_ESTADO FROM ESTADO WHERE CODIGO = '1'),
sysdate, sysdate);

commit;


SELECT CODIGO FROM ESTADO E INNER JOIN ESTADOUSUARIO ES 
ON E.PK_ESTADO = ES.FK_ESTADO 
WHERE ES.PK_ESTADOUSUARIO = (SELECT MAX 
(ESTADOUSUARIO.PK_ESTADOUSUARIO) 
FROM ESTADOUSUARIO WHERE ESTADOUSUARIO.FK_USUARIO = 1);
