use autoservis;
replace into user(user_id, email, password, name, last_name, active, broj_Telefona, role) values(0, 'admin@admin.hr', '$2a$10$byvHamNquEgFfTaXJiL/9.sCPtQel5zh0nf8vlghOal5TR.quiJTe', 'Admin', 'Admin', 1, '0990000000', 'ADMIN'); 
replace into usluga(id_usluge, ime_usluge, cijena_usluge) values (1, 'zamjena guma', 200);
replace into usluga(id_usluge, ime_usluge, cijena_usluge) values (2, 'zamjena ulja', 230);
replace into usluga(id_usluge, ime_usluge, cijena_usluge) values (3, 'zamjena filtera zraka', 100);
replace into usluga(id_usluge, ime_usluge, cijena_usluge) values (4, 'zamjena filtera ulja', 100);
replace into radno_vrijeme(id_radnog_vremena, pon_pocetak, pon_kraj, uto_pocetak, uto_kraj, sri_pocetak, sri_kraj, cet_pocetak, cet_kraj, pet_pocetak, pet_kraj)values (1, '7:00:00', '13:00:00', '12:00:00', '18:00:00', '7:00:00', '13:00:00', '12:00:00', '18:00:00', '7:00:00', '13:00:00');
replace into radno_vrijeme(id_radnog_vremena, pon_pocetak, pon_kraj, uto_pocetak, uto_kraj, sri_pocetak, sri_kraj, cet_pocetak, cet_kraj, pet_pocetak, pet_kraj)values (2, '12:00:00', '18:00:00', '7:00:00', '13:00:00', '12:00:00', '18:00:00', '7:00:00', '13:00:00', '12:00:00', '18:00:00');
replace into prijava(vrijeme_Prijave, id_Korisnika, id_Servisera, preuzeto, zavrseno, id_zamjensko) values('2019-01-02 17:08:21', 5, 10, false, false, 0);