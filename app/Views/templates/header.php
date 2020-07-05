<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>Kryptotipy.sk</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="portal zaoberajúci sa špecifickými službami v oblasti krypto.">
  <meta name="author" content="kryptotipy.sk">
  <!-- styles -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600,700" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/css/bootstrap.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/css/bootstrap-responsive.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/css/docs.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/css/prettyPhoto.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/js/google-code-prettify/prettify.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/css/flexslider.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/css/sequence.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/css/style.css" rel="stylesheet">
  <link href="<?php print_r($base_url);?>assets/color/default.css" rel="stylesheet">

  <!-- fav and touch icons -->
  <link rel="shortcut icon" href="<?php print_r($base_url);?>assets/ico/favicon.ico">
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<?php print_r($base_url);?>assets/ico/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<?php print_r($base_url);?>assets/ico/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<?php print_r($base_url);?>assets/ico/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="<?php print_r($base_url);?>assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>
  <header>
    <!-- Navbar
    ================================================== -->
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <!-- logo -->
          <a class="brand logo" href="http://www.kryptotipy.sk/"><img src="<?php print_r($base_url);?>assets/img/logo.png" alt="kryptotipy.sk"></a>
          <!-- end logo -->
          <!-- top menu -->
          <div class="navigation">
            <nav>
              <ul class="nav topnav">
                <? if($active == 'index') { ?>
                <li class="active">
                <? } else { ?>
                <li>
                <? } ?>
                  <a href="<?php print_r($base_url);?>index">Domov</a>
                </li>

                <? if($active == 'posts') { ?>
                <li class="active">
                <? } else { ?>
                <li>
                <? } ?>
                  <a href="<?php print_r($base_url);?>posts">Články</a>
                </li>

                <? if($active == 'external_services') { ?>
                <li class="active">
                <? } else { ?>
                <li>
                <? } ?>
                  <a href="<?php print_r($base_url);?>external_services">Externé služby</a>
                </li>

                <? if($active == 'dictionary') { ?>
                <li class="active">
                <? } else { ?>
                <li>
                <? } ?>
                  <a href="<?php print_r($base_url);?>dictionary">Slovník</a>
                </li>

                <? if($active == 'contact') { ?>
                <li class="active">
                <? } else { ?>
                <li>
                <? } ?>
                  <a href="<?php print_r($base_url);?>contact">Kontakt</a>
                </li>
              </ul>
            </nav>
          </div>
          <!-- end menu -->
        </div>
      </div>
    </div>
  </header>
