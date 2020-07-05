# CodeIgniter 4 Framework

## What is CodeIgniter?

CodeIgniter is a PHP full-stack web framework that is light, fast, flexible, and secure.
More information can be found at the [official site](http://codeigniter.com).

The user guide corresponding to this version of the framework can be found
[here](https://codeigniter4.github.io/userguide/).

## Versions

I am using
- CI4 in version v4.0.3.
- Minimal PHP is 7.2.
- Maria DB
- Bootstrap v2.3.0 (2012)

## Important to start with CI4

- rename env file to .env
- in .env file change production to development to make debugging working
- assets goes to public folder
- constants in Constants.php as for example ```defined('BASE_URL') || define('BASE_URL', 'http://localhost:8080/');```
- spark simplifies server booting as ```cmd php spark server```
- i did one route in Routes.php ```$routes->get('(:any)', 'Pages::render/$1');``` i didnt know ho to write / pass second parameter in routes as array and didnt want to pass it through GET request. If you find uot you can simplify Pages.php and performance.

## Server Requirements

PHP version 7.2 or higher is required, with the following extensions installed:

- [intl](http://php.net/manual/en/intl.requirements.php)
- [libcurl](http://php.net/manual/en/curl.requirements.php) if you plan to use the HTTP\CURLRequest library

Additionally, make sure that the following extensions are enabled in your PHP:

- json (enabled by default - don't turn it off)
- [mbstring](http://php.net/manual/en/mbstring.installation.php)
- [mysqlnd](http://php.net/manual/en/mysqlnd.install.php)
- xml (enabled by default - don't turn it off)
