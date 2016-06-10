INSERT INTO `profil` (`profil_id`, `profil_nom`) VALUES
(2, 'admin'),
(1, 'user');

INSERT INTO `utilisateur` (`utilisateur_id`, `utilisateur_adresse`, `utilisateur_datenaissance`, `utilisateur_actif`, `utilisateur_login`, `utilisateur_mail`, `utilisateur_motdepasse`, `utilisateur_nom`, `utilisateur_prenom`, `profil_id`) VALUES
(1, '42 rue de la rose 62000 Arras', '1960-03-08', 1, 'paul298', 'paul29@wanadoo.fr', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Dubois', 'Paul', 2),
(2, '123 rue verte 45234 Orléans', '1972-06-22', 1, 'consommateur', 'jeanpierre.b@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Papin', 'Jean-pierre', 1),
(3, '7 rue bleu', '1998-01-12', 0, 'client', 'client@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'client', 'client', 1),
(4, '7 rue bleu', '1998-01-12', 0, 'client83', 'client2@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'client3', 'client3', 1),
(5, '7 rue bleu', '1998-01-12', 0, 'client23', 'client23@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'client23', 'client23', 1),
(6, '25 rue du Mont Cenis\r\n62540 Lozinghem', '1995-06-25', 1, 'poupou25', 'poupou25@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Gerble', 'Pauline', 1),
(7, '1336 rue de la Scarpe\r\n62000 Arras', '1972-03-18', 1, 'cococcinelle', 'cococcinelle_belle@wanadoo.fr', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Marchand', 'Isabelle', 1),
(8, '89 avenue Whesley\r\n76640 Fauville-en-Caux', '1969-10-30', 1, 'mika', 'mimika@laposte.net', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Marchand', 'Michael', 1),
(9, '546 boulevard du réverbère\r\n63300 Thiers', '1953-01-01', 1, 'gerard_dupont29', 'gerard_dupont29@laposte.net', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Dupont', 'Gerard', 1),
(10, '3 rue des pommiers\r\n25145 Chazot', '1998-02-10', 1, 'totolefootballeur', 'toto10@wanadoo.fr', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Thomas', 'Thomas', 1),
(11, '23 rue de Pernes \r\n86600 Coulombiers', '1982-12-25', 1, 'miss28', 'miss28@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Delaby', 'Magalie', 1),
(12, '45 rue du bois\r\n12800 Tauriac-de-Naucelle', '1982-06-10', 1, 'orange_mecanique', 'orangemecanique10@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Montagne', 'Gilbert', 1),
(13, '56 rue de la justice\r\n58200 Cosne-Cours-sur-Loire', '1990-04-01', 1, 'thibolepasbeau', 'pasbeau@laposte.net', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', 'Canard', 'Thibaut', 1);


INSERT INTO `eboutique`.`produit` (`produit_id`, `produit_description`, `produit_enVente`, `produit_photo`, `produit_prix`, `produit_reference`) VALUES 
(1, 'macbook', true, 0xffd8ffe000104a46494600010100000100010000ffdb0084000906071412121414121,500.0,'mc32'),
(2, 'macbook PRO', true, 0xffd8ffe000104a46494600010100000100010000ffdb0084000906071412121414121,1050.0,'mc362'),
(3, 'macbook AIR', false, 0xffd8ffe000104a46494600010100000100010000ffdb0084000906071412121414121,1500.0,'mc34'),
(4, 'macbook truqué', true, 0xffd8ffe000104a46494600010100000100010000ffdb0084000906071412121414121,1050.0,'ma3624');

INSERT INTO `eboutique`.`commande` (`commande_id`, `commande_adressefacturation`, `commande_adresselivraison`, `commande_datecommande`, `remise`, `utilisateur_id`) VALUES 
('1', '7 rue christophe colomb', '7 rue christophe colomb', '2016-03-09 00:00:00', '5.0', '6'), 
('2', '123 rue verte', '123 rue verte', '2016-03-04 00:00:00', '0.0', '6'),
('3', '7 rue christophe colomb', '7 rue christophe colomb', '2016-03-09 00:00:00', '5.0', '2'), 
('4', '123 rue verte et rouge', '123 rue verte et bleu', '2016-03-04 00:00:00', '8.0', '2');
