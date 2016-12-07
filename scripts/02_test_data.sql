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
	VALUES ('Active'),
	('Complete'),
	('Cancel'),
	('Delete');
	
INSERT INTO characteristics(
	category_id, name, characteristic_group_id)
	VALUES (1, 'Brand', 1),
	(1, 'Hard Drive Capacity', 1),
	(1, 'Processor Model', 1),
	(1, 'Type of Memory (RAM)', 1),
	(1, 'Operating System', 1),
	(1, 'Graphics Cards', 1),
	(1, 'Screen Resolution', 2),
	(1, 'Screen Size', 2),
	(1, 'Display Type', 2),
	(1, 'Color', 3),
	(1, 'Dimension', 3),
	(1, 'Weight', 3),
	(1, 'Model Number', 4),
	(1, 'Battery Type', 4),
	(1, 'Expandable Memory Compatibility', 4),
	
	
	(5, 'Brand', 1),
	(5, 'Hard Drive Capacity', 1),
	(5, 'Number Of USB Port(s)', 1),
	(5, 'Number Of HDMI Outputs', 1),
	(5, 'Wireless Network Compatibility', 1),
	(5, 'Internal Hard Drive', 1),
	(5, 'Additional Accessories Included', 3),
	(5, 'Model Number', 4),
	(5, 'Color', 3),
	
	(5, 'Name', 5),
	(5, 'Synopsis', 5),
	(5, 'Product Features', 5);
	
	
INSERT INTO products(
	category_id, name, description, discount_id, price)
	VALUES (2, 'HP - ENVY x360', 'HP ENVY x360 Convertible 2-in-1 Laptop: Bring versatility to your computing with this HP convertible laptop. The Intel Core i5 processor provides powerful performance when using your favorite programs and apps, and the 1TB hard drive lets you store plenty of digital files. This HP convertible laptop rotates a full 360 degrees for customized viewing of the 15.6-inch screen.', 1, 799.99),
	(2, 'Apple - MacBook Pro', 'The new MacBook Pro is faster and more powerful than before, yet remarkably thinner and lighter.? It has the brightest, most colorful Mac notebook display ever. And it introduces the revolutionary Touch Bar�a Multi-Touch� enabled strip of glass built into the keyboard for instant access to what you want to do, when you want to do it. The new MacBook Pro is built on groundbreaking ideas. And it�s ready for yours.', 1, 1999.99),
	(2, 'Samsung - Chromebook 3', 'Samsung Chromebook 3: Unleash the power of modern computing with this Samsung Chromebook laptop. It has all the space you need with the 11.6-inch screen, which ensures portability, and 4GB of RAM and an Intel Celeron processor lets you complete basic office tasks all day long. The 16GB storage capacity means this Samsung Chromebook laptop can store files and programs for offline use.', 1, 179.00),
	(3, 'iBUYPOWER - Desktop', 'iBuyPower Desktop: Play hard with this iBUYPOWER gaming PC. With a 3.4GHz Intel i7 quad-core processor, 16GB of RAM and an NVIDIA GeForce 3GB graphics card, this machine can handle the most demanding adventures. This iBUYPOWER gaming PC has a 1TB SATA III hard drive and 120GB solid-state drive to provide ample file storage.', 1, 949.99),
	(3, 'Apple - iMac�', 'iMac features a gorgeous widescreen display, powerful Intel� processors, superfast graphics, and more. All in a stunningly thin enclosure thats only 5 mm thin at its edge.', 1, 1299.99),
	(3, 'Dell - Inspiron', 'Dell Inspiron All-In-One Computer: Glide your fingertips across the 23.8" touch screen to adjust music and video playback, surf the Web and play games, all in 1920 x 1080 resolution. Built-in Wi-Fi provides easy access to streaming entertainment.', 1, 649.99),
	(4, 'Samsung - Galaxy Tab A (2016)', 'Boost productivity with this Samsung Galaxy Tab A tablet. The 7-inch touch display provides the ideal compromise between portability and functionality, while the 8GB of internal memory lets you take photos, games, music and movies with you. The 4,000 mAh battery in this Samsung Galaxy Tab A delivers plenty of juice to get you through the day.', 1, 89.99),
	(4, 'Apple - iPad Air 2', 'The thinnest iPad ever is also the most capable. Its loaded with advanced technologies, including the Touch ID fingerprint sensor.', 1, 399.99),
	(4, 'DigiLand - Tablet', 'DigiLand Tablet: Stay connected wherever you go with this 8" tablet. Windows 10 makes Web navigation a snap, and you can house plenty of photos, music and documents with 32GB of storage space. Plus, snap clear photos of events with 2.0MP front- and rear-facing cameras.', 1, 79.99),
	(6, 'Sony - PlayStation 4 Pro', 'PS4 Pro gets you closer to your game. Heighten your experiences. Enrich your adventures. Let the super-charged PS4 Pro lead the way.', 1, 399.99),
	(6, 'Microsoft - Xbox One S', 'Own the Xbox One S Battlefield� 1 Bundle (500GB), featuring 4K Blu-ray�, 4K video streaming, High Dynamic Range, a full game download of Battlefield� 1, and one month of EA Access.', 1, 299.99),
	(7, 'Final Fantasy XV Day One Edition', 'Final Fantasy XV Day One Edition - PlayStation 4', 1, 59.99),
	(7, 'Battlefield 1', 'Battlefield 1 - PlayStation 4', 1, 59.99),
	(7, 'Battlefield 1', 'Battlefield 1 - Xbox One', 1, 59.99),
	(7, 'Call of Duty: Infinite Warfare', 'Call of Duty: Infinite Warfare - Xbox One', 1, 59.99);
	
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
	(27, 15, 'Call of Duty: Infinite Warfare will take players on an unforgettable journey as they engage in battles from Earth to beyond our atmosphere against a relentless, enemy faction that threatens our very way of life.');
	
INSERT INTO users(
	role_id, login, first_name, last_name, password, phone, email, registration_date)
	VALUES (1, 'admin', 'andrew', 'andrew', 'admin', 111111, 'admin@admin.com', current_timestamp),
	(2, 'manager', 'sergey', 'sergey', 'manager', 222222, 'manager@manager.com', current_timestamp),
	(3, 'user', 'boris', 'boris', 'user', 333333, 'user@user.com', current_timestamp),
	(3, 'user1', 'max', 'max', 'user1', 444444, 'user1@user1.com', current_timestamp);
	
INSERT INTO reviews(
	product_id, user_id, description, creation_date, raiting)
	VALUES (1, 3, 'Good notepad', current_timestamp, 9),
	(1, 4, 'Bad notepad, not working', current_timestamp, 2),
	
	(2, 3, 'Good iPad', current_timestamp, 10),
	(2, 4, 'Bad notepad, not working iPad', current_timestamp, 3),
	
	(3, 3, 'Good notepad', current_timestamp, 7),
	(3, 4, 'Bad notepad, not working', current_timestamp, 4),
	
	(4, 3, 'Nice PC', current_timestamp, 10),
	(4, 4, 'Bad PC, very big price', current_timestamp, 1),
	
	(5, 3, 'Nice Mac', current_timestamp, 10),
	(5, 4, 'Bad Mac, i hate him', current_timestamp, 1),
	
	(6, 3, 'Nice PC', current_timestamp, 6),
	(6, 4, 'Bad PC, so many lags', current_timestamp, 2),
	
	(7, 3, 'Nice tablet', current_timestamp, 8),
	(7, 4, 'Bad tablet, Samsung bad company', current_timestamp, 2),
	
	(8, 3, 'Nice tablet', current_timestamp, 10),
	(8, 4, 'Bad tablet, i dont like apple production', current_timestamp, 1),
	
	(9, 3, 'This tablet not interesting', current_timestamp, 5),
	(9, 4, 'Nice tablet, ill like this price', current_timestamp, 9),
	
	(10, 3, 'This console the best, thanks Sony', current_timestamp, 10),
	(10, 4, 'Sony, can only product TV', current_timestamp, 2),
	
	(11, 3, 'I dont like Microsoft', current_timestamp, 5),
	(11, 4, 'I dont have money, then console is bad', current_timestamp, 1),
	
	(12, 3, 'Nice Game, I liked', current_timestamp, 9),
	(12, 4, 'Bad game, I didnt have console', current_timestamp, 2),
	
	(13, 3, 'Nice Game, I liked', current_timestamp, 8),
	(13, 4, 'Bad game, I didnt have console', current_timestamp, 2),
	
	(14, 3, 'Nice Game, I liked', current_timestamp, 8),
	(14, 4, 'Bad game, I didnt have console', current_timestamp, 2),
	
	(15, 3, 'Nice Game, I liked', current_timestamp, 8),
	(15, 4, 'Bad game, I didnt have console', current_timestamp, 2);
	
INSERT INTO sales_orders(
	user_id, creation_date, "limit", order_status_id)
	VALUES (3, current_timestamp, 5000, 1),
	(3, current_timestamp, 10000, 2),
	(4, current_timestamp, 500000, 3),
	(4, current_timestamp, 1000000, 4);
	
INSERT INTO order_items(
	product_id, sales_order_id, quantity)
	VALUES (14, 1, 2);
	
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
	VALUES ('About', 'Our Shop is a leading provider of technology products, services and solutions. The company offers expert service at an unbeatable price more than 1.5 billion times a year to the consumers, small business owners and educators who visit our stores. The shop has operations in the Russia where more than 70 percent of the population.');
