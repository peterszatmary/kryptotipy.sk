<?php namespace App\Models;

use CodeIgniter\Model;

class PostModel extends Model
{
  protected $table         = 'post';
  protected $primaryKey    = 'id';
  protected $allowedFields = ['id', 'url', 'source', 'content', 'title', 'i18n', 'disabled', 'birthday', 'external', 'labels'];
  protected $returnType    = 'App\Entities\Post';
}
