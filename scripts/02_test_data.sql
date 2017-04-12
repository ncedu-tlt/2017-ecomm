INSERT INTO categories(
  parent_id, name, description)
	VALUES (NULL, 'Computers', 'Main Computers Category'),
    (1, 'Laptops', 'Portable power in your hands for on-the-go work and play.'),
    (1, 'Desktops', 'Shop sleek towers, complete packages, or compact all-in-one designs.'),
    (1, 'Tablets', 'Go-anywhere devices in your choice of brands, operating systems and sizes.'),
    (NULL, 'Video Games', 'Main Video Games Category'),
    (5, 'Video game console', 'Main Video Games Category'),
    (5, 'Games', 'Main Video Games Category');

INSERT INTO characteristic_groups(name)
	VALUES ('Specifications'),
	('Display'),
	('General'),
	('Other'),
	('Overview');

INSERT INTO discount(name, value)
	VALUES ('No discount', 0),
	('5%', 5),
	('10%', 10),
	('15%', 15),
	('20%', 20),
	('25%', 25),
	('30%', 30),
	('35%', 35),
	('40%', 40),
	('45%', 45),
	('50%', 50),
	('60%', 60),
	('75%', 75),
	('80%', 80),
	('90%', 90),
	('Present', 100);
	
INSERT INTO roles(name)
	VALUES ('Administrators'),
	('Managers'),
	('Users');
	
INSERT INTO order_statuses(name)
	VALUES ('Entering'),
	('Submited'),
	('Completed');
	
INSERT INTO characteristics(
	category_id, name, characteristic_group_id, filterable)
	VALUES (1, 'Brand', 1, true),
	(1, 'Hard Drive Capacity', 1, false),
	(1, 'Processor Model', 1, true),
	(1, 'Type of Memory (RAM)', 1, false),
	(1, 'Operating System', 1, false),
	(1, 'Graphics Cards', 1, false),
	(1, 'Screen Resolution', 2, false),
	(1, 'Screen Size', 2, false),
	(1, 'Display Type', 2, false),
	(1, 'Color', 3, false),
	(1, 'Dimension', 3, true),
	(1, 'Weight', 3, false),
	(1, 'Model Number', 4, false),
	(1, 'Battery Type', 4, false),
	(1, 'Expandable Memory Compatibility', 4, false),
	
	(5, 'Brand', 1, true),
	(5, 'Hard Drive Capacity', 1, true),
	(5, 'Number Of USB Port(s)', 1, false),
	(5, 'Number Of HDMI Outputs', 1, false),
	(5, 'Wireless Network Compatibility', 1, false),
	(5, 'Internal Hard Drive', 1, false),
	(5, 'Additional Accessories Included', 3, false),
	(5, 'Model Number', 4, false),
	(5, 'Color', 3, false),
	(5, 'Name', 5, false),
	(5, 'Synopsis', 5, false),
	(5, 'Product Features', 5, false),
	(1, 'Images', 1, false);
	
	
INSERT INTO products (product_id, category_id, name, description, discount_id, price)
VALUES (2, 2, 'Apple - MacBook Pro', 'The new MacBook Pro is faster and more powerful than before, yet remarkably thinner and lighter.¹ It has the brightest, most colorful Mac notebook display ever. And it introduces the revolutionary Touch Bar—a Multi-Touch– enabled strip of glass built into the keyboard for instant access to what you want to do, when you want to do it. The new MacBook Pro is built on groundbreaking ideas. And it’s ready for yours.', 1, 1999.99),
(5, 3, 'Apple - iMac®', 'iMac features a gorgeous widescreen display, powerful Intel® processors, superfast graphics, and more. All in a stunningly thin enclosure thats only 5 mm thin at its edge.', 1, 1299.99),
(6, 3, 'Dell - Inspiron', 'Dell Inspiron All-In-One Computer: Glide your fingertips across the 23.8" touch screen to adjust music and video playback, surf the Web and play games, all in 1920 x 1080 resolution. Built-in Wi-Fi provides easy access to streaming entertainment.', 1, 649.99),
(8, 4, 'Apple - iPad Air 2', 'The thinnest iPad ever is also the most capable. Its loaded with advanced technologies, including the Touch ID fingerprint sensor.', 1, 399.99),
(9, 4, 'DigiLand - Tablet', 'DigiLand Tablet: Stay connected wherever you go with this 8" tablet. Windows 10 makes Web navigation a snap, and you can house plenty of photos, music and documents with 32GB of storage space. Plus, snap clear photos of events with 2.0MP front- and rear-facing cameras.', 1, 79.99),
(11, 6, 'Microsoft - Xbox One S', 'Own the Xbox One S Battlefield™ 1 Bundle (500GB), featuring 4K Blu-ray™, 4K video streaming, High Dynamic Range, a full game download of Battlefield™ 1, and one month of EA Access.', 1, 299.99),
(12, 7, 'Final Fantasy XV Day One Edition', 'Final Fantasy XV Day One Edition - PlayStation 4', 1, 59.99),
(14, 7, 'Battlefield 1', 'Battlefield 1 - Xbox One', 1, 59.99),
(15, 7, 'Call of Duty: Infinite Warfare', 'Call of Duty: Infinite Warfare - Xbox One', 1, 59.99),
(3, 2, 'Samsung - Chromebook 3', 'Samsung Chromebook 3: Unleash the power of modern computing with this Samsung Chromebook laptop. It has all the space you need with the 11.6-inch screen, which ensures portability, and 4GB of RAM and an Intel Celeron processor lets you complete basic office tasks all day long. The 16GB storage capacity means this Samsung Chromebook laptop can store files and programs for offline use.', 2, 179.00),
(4, 3, 'iBUYPOWER - Desktop', 'iBuyPower Desktop: Play hard with this iBUYPOWER gaming PC. With a 3.4GHz Intel i7 quad-core processor, 16GB of RAM and an NVIDIA GeForce 3GB graphics card, this machine can handle the most demanding adventures. This iBUYPOWER gaming PC has a 1TB SATA III hard drive and 120GB solid-state drive to provide ample file storage.', 5, 949.99),
(7, 4, 'Samsung - Galaxy Tab A (2016)', 'Boost productivity with this Samsung Galaxy Tab A tablet. The 7-inch touch display provides the ideal compromise between portability and functionality, while the 8GB of internal memory lets you take photos, games, music and movies with you. The 4,000 mAh battery in this Samsung Galaxy Tab A delivers plenty of juice to get you through the day.', 8, 89.99),
(10, 6, 'Sony - PlayStation 4 Pro', 'PS4 Pro gets you closer to your game. Heighten your experiences. Enrich your adventures. Let the super-charged PS4 Pro lead the way.', 9, 399.99),
(13, 7, 'Battlefield 1', 'Battlefield 1 - PlayStation 4', 4, 59.99),
(1, 2, 'HP - ENVY x360', 'HP ENVY x360 Convertible 2-in-1 Laptop: Bring versatility to your computing with this HP convertible laptop. The Intel Core i5 processor provides powerful performance when using your favorite programs and apps, and the 1TB hard drive lets you store plenty of digital files. This HP convertible laptop rotates a full 360 degrees for customized viewing of the 15.6-inch screen.', 9, 799.00);
	
INSERT INTO characteristic_values(
	characteristic_id, product_id, value)
	VALUES (1, 1, 'HP'),
	(2, 1, '1000 gigabytes'),
	(3, 1, 'Intel 7th Generation Core i5, 3.3 gigahertz'),
	(4, 1, '12 gigabytes'),
	(5, 1, 'Windows 10'),
	(6, 1, 'AMD RADEON 6550M'),
	(7, 1, '1920 x 1080 (Full HD)'),
	(8, 1, '15.6 inches'),
	(9, 1, 'Full HD Widescreen LED'),
	(10, 1, 'Silver'),
	(11, 1, '0.7x15x9.8 inches'),
	(12, 1, '4.78 pounds'),
	(13, 1, 'm6-aq103dx'),
	(14, 1, 'Lithium-ion'),
	(15, 1, 'microSD'),
	
	(1, 2, 'Apple'),
	(2, 2, '512 gigabytes'),
	(3, 2, 'Intel, 3.3 gigahertz'),
	(4, 2, '8 gigabytes'),
	(5, 2, 'Mac OS'),
	(6, 2, 'Intel Iris Graphics 550'),
	(8, 2, '13.3 inches'),
	(9, 2, 'Other'),
	(10, 2, 'Silver'),
	(11, 2, '0.59x11.97x8.36 inches'),
	(12, 2, '3.02 pounds'),
	(13, 2, 'MNQG2LL/A'),
	(14, 2, 'Lithium-polymer'),
	(15, 2, 'No'),

	(1, 3, 'Samsung'),
	(2, 3, '16 gigabytes'),
	(3, 3, 'Intel Celeron, 1.6 gigahertz'),
	(4, 3, '4 gigabytes'),
	(5, 3, 'Chrome OS'),
	(6, 3, 'Intel� HD Graphics'),
	(7, 3, '1366 x 768 (HD)'),
	(8, 3, '11.6 inches'),
	(9, 3, 'Widescreen LED'),
	(10, 3, 'Black'),
	(11, 3, '0.7x11.37x8.04 inches'),
	(12, 3, '2.54 pounds'),
	(13, 3, 'XE500C13-K02US'),
	(14, 3, 'Lithium-ion'),
	(15, 3, 'microSD'),
	
	(1, 4, 'iBUYPOWER'),
	(2, 4, '1120 gigabytes'),
	(3, 4, 'Intel 7th Generation Core i7, 3.4 gigahertz'),
	(4, 4, '16 gigabytes'),
	(5, 4, 'Windows 10'),
	(6, 4, 'NVIDIA GeForce GTX 1060 graphics, 3Gb'),
	(10, 4, 'Multi'),
	(11, 4, '17x7.9x17.5 inches'),
	(12, 4, '30 pounds'),
	(13, 4, 'BB921'),
	
	(1, 5, 'Apple'),
	(2, 5, '1000 gigabytes'),
	(3, 5, 'Intel, 2.8 gigahertz'),
	(4, 5, '8 gigabytes'),
	(5, 5, 'Windows 10'),
	(6, 5, 'Intel� Iris Pro Graphics 6200'),
	(10, 5, 'Silver'),
	(11, 5, '21.26x25.2x8.9 inches'),
	(12, 5, '18.298 pounds'),
	(13, 5, 'MK442LL/A'),
	
	(1, 6, 'DELL'),
	(2, 6, '1000 gigabytes'),
	(3, 6, 'AMD A8-Series, 2.2 gigahertz'),
	(4, 6, '8 gigabytes'),
	(5, 6, 'Windows 10'),
	(6, 6, 'AMD Radeon R5'),
	(7, 6, '1920 x 1080 (Full HD)'),
	(8, 6, '23.8 inches'),
	(9, 6, 'Touchscreen LED'),
	(10, 6, 'Black/White'),
	(11, 6, '14.25x22.7x1.51 inches'),
	(12, 6, '16.47 pounds'),
	(13, 6, 'I3455-10041WHT'),
	
	(1, 7, 'Samsung'),
	(2, 7, '8 gigabytes'),
	(3, 7, 'Intel Atom, 1.3 gigahertz'),
	(5, 7, 'Android 5.1 Lollipop'),
	(7, 7, '1280 x 800'),
	(8, 7, '7 inches'),
	(10, 7, 'Silver'),
	(11, 7, '7.4x4.3x0.3 inches'),
	(12, 7, '10 ounces'),
	(13, 7, 'SM-T280NZSAXAR'),
	(14, 7, '4000 milliampere hours'),
	(15, 7, 'microSDXC'),
	
	(1, 8, 'Apple'),
	(2, 8, '32 gigabytes'),
	(3, 8, 'Apple A8X'),
	(5, 8, 'Apple iOS 8.1'),
	(7, 8, '2048 x 1536'),
	(8, 8, '9.7 inches'),
	(10, 8, 'Gold'),
	(11, 8, '9.4x6.6 inches'),
	(12, 8, '7 ounces'),
	(13, 8, 'MNV72LL/A'),
	(14, 8, 'Lithium-ion'),
	(15, 8, 'No'),
	
	(1, 9, 'DigiLand'),
	(2, 9, '32 gigabytes'),
	(3, 9, 'Intel Atom 3735F, 1.3 gigahertz'),
	(5, 9, 'Windows 10'),
	(7, 9, '1280 x 800'),
	(8, 9, '8 inches'),
	(10, 9, 'Black'),
	(11, 9, '0.4x8.2x4.9 inches'),
	(12, 9, '13.44 ounces'),
	(13, 9, 'DL808W'),
	(14, 9, 'Lithium-polymer'),
	(15, 9, 'microSD'),
	
	(16, 10, 'Sony'),
	(17, 10, '1000 gigabytes'),
	(18, 10, '3'),
	(19, 10, '1'),
	(20, 10, 'Bluetooth, Wi-Fi'),
	(21, 10, 'Yes, HDD'),
	(22, 10, 'DUALSHOCK 4 wireless controller; Mono headset; Premium HDMI cable; USB and power cables'),
	(23, 10, 'SONY PLAYSTATION 4 PRO CONSOLE'),
	(24, 10, 'Black'),
	
	(16, 11, 'Microsoft'),
	(17, 11, '500 gigabytes'),
	(18, 11, '3'),
	(19, 11, '1'),
	(20, 11, 'Wi-Fi'),
	(21, 11, 'Yes, HDD'),
	(22, 11, 'HDMI Cable, AC Power cable, Xbox Live Gold 14-day membership trial, Battlefield 1 full game download, Xbox Wireless Controller'),
	(23, 11, 'Xbox One S 500GB Battlefield� 1 Console Bundle'),
	(24, 11, 'White'),
	
	(25, 12, 'Final Fantasy XV Day One Edition - PlayStation 4'),
	(26, 12, 'Fight the imperial army to reclaim Eos in Final Fantasy XV. You are the crown prince who has been cast aside, so you must travel through Eos while battling extraordinary new monsters, making friends and discovering your skills.'),
	(27, 12, 'Enter the world of FINAL FANTASY XV, and experience epic action-packed battles along your journey of discovery. '),
	
	(25, 13, 'Battlefield 1 - PlayStation 4'),
	(26, 13, 'Battle players in new war environments with Battlefield 2016 for the PlayStation 4. '),
	(27, 13, 'Be a part of the greatest battles ever known to man. From the heavily defended Alps to the scorching deserts of Arabia, war is raging on an epic scale on land, air and sea.'),
	
	(25, 14, 'Battlefield 1 - Xbox One'),
	(26, 14, 'Battle players in new war environments with Battlefield 2016 for the Xbox One. '),
	(27, 14, 'Be a part of the greatest battles ever known to man. From the heavily defended Alps to the scorching deserts of Arabia, war is raging on an epic scale on land, air and sea.'),
	
	(25, 15, 'Call of Duty: Infinite Warfare - Xbox One'),
	(26, 15, 'Infinity Ward, the award-winning studio that helped create the blockbuster Call of Duty� franchise, reaches new heights with Call of Duty�: Infinite Warfare.'),
	(27, 15, 'Call of Duty: Infinite Warfare will take players on an unforgettable journey as they engage in battles from Earth to beyond our atmosphere against a relentless, enemy faction that threatens our very way of life.'),

	(28, 1, '\images\cat_id1\cat_id2\prod_id1\HP_ENVY_x360.png, \images\cat_id1\cat_id2\prod_id1\HP_ENVY_x360.png'),
	(28, 2, '\images\cat_id1\cat_id2\prod_id2\macbookpro.png, \images\cat_id1\cat_id2\prod_id2\macbookpro.png,\images\cat_id1\cat_id2\prod_id2\macbookpro.png'),
	(28, 5, '\images\cat_id1\cat_id3\prod_id5\imac.png');
	
INSERT INTO users(user_id,
	role_id, first_name, last_name, password, phone, email, registration_date, recovery_hash, user_avatar)
	VALUES (36, 3, 'a', NULL,  42767516990368493138776584305024125808, NULL, 'user@mail.ru', '2017-01-18', 4247867252, '/images/useravatars/unknownuser/unknownuser.png'),
(60, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user1234@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(40, 3, 'Alex', NULL, 42767516990368493138776584305024125808, NULL,'user1234@mail.ru', '2017-01-25', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(41, 3, 'Bobffg', 'Gagaf', 172615877915217560892406575915100721237, NULL, 'lordever@mail.ru', '2017-02-02', 7683130118, '/images/useravatars/unknownuser/unknownuser.png'),
(4, 3, 'max', 'max', 316448663440605426369181748590440883182, 444444, 'user@mail.ru', '2016-12-05', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(57, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(66, 3, NULL, NULL, 316448663440605426369181748590440883182, NULL, 'user@admin.com', '2017-02-11', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(68, 3, NULL, NULL, 316448663440605426369181748590440883182, NULL, 'user@mail.admin.ru', '2017-02-12', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(6, 2, 'alexander', 'samon', 62303972861003552617591658957390934968, 79362673456, 'hdg@mail.ru', '2016-12-12', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(75, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, '4235@mail.ru', '2017-02-14', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(37, 1, 'User', 'Test', 12707736894140473154801792860916528374, NULL, 'test@test.com', '2017-01-19', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(65, 3, NULL, NULL, 173447602773428053556316684567667297915, NULL, 'kastiel63@gmail.com', '2017-02-09', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(5, 1, NULL, NULL, 173447602773428053556316684567667297915, NULL, 'test@email.ru', '2016-12-05', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(1, 1, 'andrew', 'andrew', 32254982671930602039565692807661004282, 111111, 'admin@admin.com', '2016-12-05', 0154541624, '/images/useravatars/unknownuser/unknownuser.png'),
(69, 3, 'Donald', 'Trump', 172615877915217560892406575915100721237, NULL, 'crab16_91@mail.ru', '2017-02-12', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(67, 3, NULL, NULL, 316448663440605426369181748590440883182, NULL, 'admin@user.com', '2017-02-12', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(71, 3, NULL, NULL, 172615877915217560892406575915100721237, NULL, 'test@testtest.com', '2017-02-14', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(56, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(3, 3, 'boris', 'boris', 316448663440605426369181748590440883182, 333333, 'user@user.com', '2016-12-05', 1631121811, '/images/useravatars/unknownuser/unknownuser.png'),
(50, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(58, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(47, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(53, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(52, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(55, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(49, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(43, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(45, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(54, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(44, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(51, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(48, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(46, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user123@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(73, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user999@mail.ru', '2017-02-14', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(63, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user@mail.ru', '2017-02-09', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(42, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(61, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user@mail.ru', '2017-02-09', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(59, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'user@mail.ru', '2017-02-08', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(64, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, '123321@mail.ru', '2017-02-09', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(74, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, '12345678@mail.ru', '2017-02-14', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(62, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, '12345@mail.ru', '2017-02-09', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(33, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, '123@mail.ru', '2016-12-26', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(76, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, '76584@mail.ru', '2017-02-15', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(72, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'Haba321@mail.com', '2017-02-14', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(35, 3, 'Ivan', 'Alekseev', 172615877915217560892406575915100721237, NULL, 'jegius@gmail.com', '2017-01-16', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(2, 2, 'sergey', 'sergey', 38559796714846015341288105611672564112, 222222, 'manager@manager.com', '2016-12-05', 6816810077, '/images/useravatars/unknownuser/unknownuser.png'),
(70, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, 'Haba@mail.com', '2017-02-13', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(77, 3, NULL, NULL, 42767516990368493138776584305024125808, NULL, '987@mail.ru', '2017-02-15', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(78, 3, 'Alexf', 'Mikles', 172615877915217560892406575915100721237, NULL, 'admin@gmail.comd', '2017-02-21', NULL, '/images/useravatars/unknownuser/unknownuser.png'),
(79, 3, 'Alex', 'Syrus', 172615877915217560892406575915100721237, NULL, 'lordever@mail.ru', '2017-02-25', NULL, '/images/useravatars/unknownuser/unknownuser.png');

INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (1, 37, 'gfdgdfgd', '2017-03-06', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (2, 35, 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).ready for yours.', '2017-02-22', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (1, 78, 'so good', '2017-02-23', 4);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (1, 69, 'It''s ideal notebook!', '2017-02-27', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (1, 4, 'Bad notepad, not working', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (4, 4, 'Bad PC, very big price', '2016-12-05', 1);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (5, 4, 'Bad Mac, i hate him', '2016-12-05', 1);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (6, 4, 'Bad PC, so many lags', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (7, 4, 'Bad tablet, Samsung bad company', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (8, 4, 'Bad tablet, i dont like apple production', '2016-12-05', 1);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (10, 4, 'Sony, can only product TV', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (11, 4, 'I dont have money, then console is bad', '2016-12-05', 1);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (12, 4, 'Bad game, I didnt have console', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (13, 4, 'Bad game, I didnt have console', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (14, 4, 'Bad game, I didnt have console', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (15, 4, 'Bad game, I didnt have console', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (5, 3, 'Nice Mac', '2016-12-05', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (4, 3, 'Nice PC', '2016-12-05', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (8, 3, 'Nice tablet', '2016-12-05', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (2, 3, 'Good iPad', '2016-12-05', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (10, 3, 'This console the best, thanks Sony', '2016-12-05', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (9, 4, 'Nice tablet, ill like this price', '2016-12-05', 4);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (12, 3, 'Nice Game, I liked', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (1, 3, 'Good notepad', '2016-12-05', 4);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (14, 3, 'Nice Game, I liked', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (15, 3, 'Nice Game, I liked', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (13, 3, 'Nice Game, I liked', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (7, 3, 'Nice tablet', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (3, 3, 'Good notepad', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (6, 3, 'Nice PC', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (9, 3, 'This tablet not interesting', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (11, 3, 'I dont like Microsoft', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (3, 4, 'Bad notepad, not working', '2016-12-05', 3);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (2, 4, 'Bad notepad, not working iPad', '2016-12-05', 2);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (1, 5, 'Very Good NoteBook.', '2016-12-05', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (1, 35, 't is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', '2017-03-07', 5);
INSERT INTO public.reviews (product_id, user_id, description, creation_date, raiting) VALUES (7, 35, 'Boost productivity with this Samsung Galaxy Tab A tablet. The 7-inch touch display provides the ideal compromise between portability and functionality, while the 8GB of internal memory lets you take photos, games, music and movies with you. The 4,000 mAh battery in this Samsung Galaxy Tab A delivers plenty of juice to get you through the day.', '2017-02-10', 5);
	
INSERT INTO sales_orders (sales_order_id, user_id, creation_date, "limit", order_status_id) VALUES (159, 41, '2017-02-20', 0.00, 1),
 (158, 41, '2017-02-20', null, 1),
 (157, 41, '2017-02-13', null, 2),
 (153, 41, '2017-02-12', null, 1),
 (154, 41, '2017-02-12', 0.00, 1),
 (155, 41, '2017-02-12', null, 1),
 (156, 41, '2017-02-12', null, 1),
 (152, 41, '2017-02-12', 800.00, 3),
 (160, 41, '2017-02-21', 3000.00, 1),
 (164, 41, '2017-02-28', null, 1),
 (165, 3, '2017-03-06', null, 2),
 (166, 35, '2017-03-06', null, 1),
 (167, 3, '2017-03-06', null, 2);
	
INSERT INTO order_items (product_id, sales_order_id, quantity, standard_price) VALUES (4, 166, 13, 760),
(1, 167, 3, 480),
(10, 167, 1, 240),
(7, 167, 1, 58),
(10, 153, 1, 240),
(1, 153, 1, 480),
(2, 153, 1, 1999),
(1, 166, 6, 480),
(2, 166, 1, 1999),
(10, 158, 1, 240),
(2, 158, 1, 1999),
(1, 158, 1, 480),
(7, 158, 4, 58),
(1, 157, 1, null),
(1, 152, 1, null),
(1, 154, 1, 480),
(1, 159, 1, 480),
(10, 159, 1, 240),
(1, 156, 6, 480),
(1, 160, 1, null),
(7, 156, 12, 58),
(7, 160, 4, null),
(1, 165, 1, 480),
(10, 166, 1, 240),
(13, 166, 1, 51),
(7, 166, 5, 58),
(3, 166, 2, 171);

INSERT INTO recommended_products(
	source_product_id, target_product_id)
	VALUES (1, 2),
	(1, 3),
	(2, 1),
	(2, 3),
	(3, 1),
	(3, 2),
	(4, 5),
	(4, 6),
	(5, 4),
	(5, 6),
	(6, 4),
	(6, 5),
	(7, 8),
	(7, 9),
	(8, 7),
	(8, 9),
	(9, 7),
	(9, 8),
	(10, 11),
	(11, 10),
	(12, 13),
	(12, 14),
	(12, 15),
	(13, 12),
	(13, 14),
	(13, 15),
	(14, 12),
	(14, 13),
	(14, 15),
	(15, 12),
	(15, 13),
	(15, 14);
		
INSERT INTO properties(
	property_id, value)
	VALUES ('About', 'Our Shop is a leading provider of technology products, services and solutions. The company offers expert service at an unbeatable price more than 1.5 billion times a year to the consumers, small business owners and educators who visit our stores. The shop has operations in the Russia where more than 70 percent of the population.'),
  ('Email',	'inbox@shop.com'),
  ('Phone', '+1 888-222-1010'),
  ('Rights',	'© 2005-2017, Shop, LLC. All rights reserved.'),
  ('facebookUrl',	'https://facebook.com'),
  ('google+Url',	'https://plus.google.com'),
  ('twitterUrl',	'https://twitter.com'),
  ('vkUrl',	'https://vk.com');