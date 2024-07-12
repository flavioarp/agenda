use dbagenda;


/* CRUD CREATE */
INSERT INTO contatos (nome, fone, email) VALUES ('Bill Gates', '99999-1111', 'bill-gates@microsoft.com');

/* CRUD READ */
SELECT * FROM contatos;
SELECT * FROM contatos ORDER BY nome;