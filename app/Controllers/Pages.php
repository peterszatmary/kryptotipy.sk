<?php namespace App\Controllers;

use CodeIgniter\Controller;

# it is written like this because i had problem with second parameter in routes as array and didnt want to pass it through GET request

class Pages extends Controller
{
    private $PAGE_ONE_POST = "one_post";
    private $PAGE_POSTS = "posts";
    private $PAGE_INDEX = "index";
    private $PAGE_EXTERNAL_SERVICES = "external_services";
    private $PAGE_DICTIONARY = "dictionary";
    private $PAGE_CONTACT = "contact";

    public function index()
    {
        return render($this->PAGE_INDEX);
    }

    public function posts()
    {
        return render($this->PAGE_POSTS);
    }

    public function dictionary()
    {
        return render($this->PAGE_DICTIONARY);
    }

    public function one_post()
    {
        return render($this->PAGE_ONE_POST);
    }

    public function external_services()
    {
        return render($this->PAGE_EXTERNAL_SERVICES);
    }



    public function render($page)
    {
         $data['base_url'] = BASE_URL;
         $header = 'header';

         if ( ! is_file(APPPATH.'/Views/pages/'.$page.'.php'))
         {
            $data['title'] = "404" ;
            echo view('templates/'.$header, $data);
            echo view('pages/404', $data);
            echo view('templates/footer', $data);
         }

         else if ($page == $this->PAGE_INDEX)
         {
            $data['title'] = $this->PAGE_INDEX;
            $data['active'] = $this->PAGE_INDEX;
            $header = 'index_header';
         }

         else if ($page == $this->PAGE_POSTS)
         {
             $data['title'] = $this->PAGE_POSTS;
             $data['active'] = $this->PAGE_POSTS;
         }

         else if ($page == $this->PAGE_ONE_POST)
         {
             $data['title'] = $this->PAGE_ONE_POST;
             $data['active'] = $this->PAGE_ONE_POST;
         }

         else if ($page == $this->PAGE_DICTIONARY)
         {
             $data['title'] = $this->PAGE_DICTIONARY;
             $data['active'] = $this->PAGE_DICTIONARY;
         }

         else if ($page == $this->PAGE_EXTERNAL_SERVICES)
         {
             $data['title'] = $this->PAGE_EXTERNAL_SERVICES;
             $data['active'] = $this->PAGE_EXTERNAL_SERVICES;
         }

         else if ($page == $this->PAGE_CONTACT)
         {
             $data['title'] = $this->PAGE_CONTACT;
             $data['active'] = $this->PAGE_CONTACT;
         }
         echo view('templates/'.$header, $data);
         echo view('pages/'.$page, $data);
         echo view('templates/footer', $data);
    }

    #fill_data
}
