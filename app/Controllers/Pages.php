<?php namespace App\Controllers;

use CodeIgniter\Controller;

# it is written like this because i had problem with second parameter in routes as array and didnt want to pass it through GET request

class Pages extends Controller {
    private $PAGE_ONE_POST = "one_post";
    private $PAGE_POSTS = "posts";
    private $PAGE_INDEX = "index";
    private $PAGE_EXTERNAL_SERVICES = "external_services";
    private $PAGE_DICTIONARY = "dictionary";
    private $PAGE_CONTACT = "contact";
    private $db;

    public function __construct() {
        $this->db = \Config\Database::connect();
    }

    public function index() {
        return render($this->PAGE_INDEX);
    }

    public function fill_index() {
        $data['title'] = $this->PAGE_INDEX;
        $data['active'] = $this->PAGE_INDEX;
        $data['base_url'] = BASE_URL;
        $data['header'] = 'index_header';
        return $data;
    }

    public function posts() {
        return render($this->PAGE_POSTS);
    }

    public function fill_posts() {
        $data['title'] = $this->PAGE_POSTS;
        $data['active'] = $this->PAGE_POSTS;
        $data['base_url'] = BASE_URL;
        $data['header'] = 'header';

        $t = $this->db->table('post')->get()->getResult();
        $data['posts'] = $t[0];
        //log_message('info', $data['posts']);
        return $data;
    }

    public function dictionary() {
        return render($this->PAGE_DICTIONARY);
    }

    public function fill_dictionary() {
        $data['title'] = $this->PAGE_DICTIONARY;
        $data['active'] = $this->PAGE_DICTIONARY;
        $data['base_url'] = BASE_URL;
        $data['header'] = 'header';
        return $data;
    }

    public function external_services() {
        return render($this->PAGE_EXTERNAL_SERVICES);
    }

    public function fill_external_services() {
        $data['title'] = $this->PAGE_EXTERNAL_SERVICES;
        $data['active'] = $this->PAGE_EXTERNAL_SERVICES;
        $data['base_url'] = BASE_URL;
        $data['header'] = 'header';
        return $data;
    }

    public function contact() {
        return render($this->PAGE_CONTACT);
    }

    public function fill_contact() {
        $data['title'] = $this->PAGE_CONTACT;
        $data['active'] = $this->PAGE_CONTACT;
        $data['base_url'] = BASE_URL;
        $data['header'] = 'header';
        return $data;
    }

    public function fill_404() {
        $data['header'] = 'header';
        $data['base_url'] = BASE_URL;
        $data['active'] = $this->PAGE_INDEX;
        $data['title'] = "404";
        return $data;
    }

    public function render($page) {
         if ( ! is_file(APPPATH.'/Views/pages/'.$page.'.php')) {
            $data = $this->fill_404();
            echo view('templates/'.$data['header'], $data);
            echo view('pages/'.$data['title'], $data);
            echo view('templates/footer', $data);
         }

         else if ($page == $this->PAGE_INDEX) {
            $data = $this->fill_index();
         }

         else if ($page == $this->PAGE_POSTS) {
             $data = $this->fill_posts();
         }

         else if ($page == $this->PAGE_DICTIONARY) {
              $data = $this->fill_dictionary();
         }

         else if ($page == $this->PAGE_EXTERNAL_SERVICES) {
              $data = $this->fill_external_services();
         }

         else if ($page == $this->PAGE_CONTACT) {
              $data = $this->fill_contact();
         }
         echo view('templates/'.$data['header'], $data);
         echo view('pages/'.$page, $data);
         echo view('templates/footer', $data);
    }
}
