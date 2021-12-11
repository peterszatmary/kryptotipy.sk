SELECT p.id, p.url, p.title, p.source, p.birthday, p.img_src, p.source_render,p.i18n, p.clicks, p.likes FROM post p JOIN post_index pi ON pi.post_id = p.id JOIN label l ON l.id = pi.label_id WHERE p.i18n in ('US', 'CA', 'GB', 'SK', 'CZ', 'HU', 'PL', 'NL', 'ES', 'SE') and l.name in ('airdrops') AND p.img_src is not null and pi.count=(select max(u.count) from post_index as u where u.post_id = p.id) and p.disabled = 0 order by p.birthday DESC LIMIT 0,60;
: 6 sec - 7.12.2021 - 155 000 posts
: 20 sec - 10.12.2021 - 160 000 posts
po optimalizacii
: 6 sec - 11.12.2021 - 160 000 posts

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