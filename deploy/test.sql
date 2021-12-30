SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render,p.i18n, p.clicks, p.likes FROM post p JOIN post_index pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id WHERE p.i18n in ('US', 'CA', 'GB', 'SK', 'CZ', 'HU', 'PL', 'NL', 'ES', 'SE') and l.name in ('airdrops') AND p.img_src is not null and pi.count=(select max(u.count) from post_index as u where u.post_id = p.id) and p.disabled = 0 order by p.birthday DESC LIMIT 0,60;

: 6 sec - 7.12.2021 - 155 000 posts
: 20 sec - 10.12.2021 - 160 000 posts
po optimalizacii
: 6 sec - 11.12.2021 - 160 000 posts

po dalsej optimalizacii z view
: 0.088 sec 160 000 posts


SELECT p.id, p.post_id, p.post_url, p.post_title, p.post_title_render, p.post_source, p.post_birthday, p.post_source_image,  p.post_source_render,post_original_image, p.post_lang, p.post_clicks, p.post_likes,p.label_name_1, p.label_relevance_1,p.label_name_2, p.label_relevance_2 ,p.label_name_3, p.label_relevance_3,p.label_name_4, p.label_relevance_4  FROM post_with_label p  WHERE p.post_disabled = 0  AND p.post_lang IN ( 'US', 'CA', 'GB', 'SK', 'CZ', 'HU', 'PL', 'NL', 'ES', 'SE') and p.label_name_1 in ( 'airdrops') AND p.post_source_image is not null   ORDER BY p.post_birthday DESC  LIMIT 0,60 ;



SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render,p.i18n, p.clicks, p.likes FROM post p JOIN post_index pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id WHERE p.i18n in ('US', 'CA', 'GB') and l.name in ('airdrops') AND p.img_src is not null and pi.count=(select max(u.count) from post_index as u where u.post_id = p.id) and p.disabled = 0 order by p.birthday DESC LIMIT 0,60;
: 3 sec - 7.12.2021 - 155 000 posts
: 7 sec - 10.12.2021 - 160 000 posts
po optimalizacii
: 4.3 sec - 11.12.2021 - 160 000 posts

SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render,p.i18n, p.clicks, p.likes FROM post p JOIN post_index pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id WHERE p.i18n in ('US') and l.name in ('airdrops') AND p.img_src is not null and pi.count=(select max(u.count) from post_index as u where u.post_id = p.id) and p.disabled = 0 order by p.birthday DESC LIMIT 0,60;
: 2.8 sec - 7.12.2021 - 155 000 posts
: 4 sec - 10.12.2021 - 160 000 posts
po optimalizacii
: 3.9 sec - 11.12.2021 - 160 000 posts