BlueXML SIDE Requirements : Prototype
==========================================================================

Table of Contents:
------------------
- Introduction
- Prerequisites
- Pointers & License Notice

Introduction:
-------------
The result of this interpretation aims to validate the requirements definition defined in the model. To check it,
it is possible to generate a complete PHP project usable by functional experts. This interpretation should be used
after the high-level validation using mind maps suited to managers.

Prerequisites:
--------------
In order to deploy the generated web project, follow the steps:
- Install a PHP server like Apache including MySQL (To simplify this step, you can install a full package including
  Apache, PHP and MySQL like LAMPP for Linux or XAMPP for Windows and Mac). 
- Create the database 'reqs_prototype' (using for example phpMyAdmin)
- Open the generated ANT script called 'buildToExecute.xml' and change following parameters :
  . sql.driver : This is the mysql driver used. Attention : it is possible that your ANT program doesn't contain this
                 library. It it is not the case, you can download it on www.mysql.com.
  . sql.url : You must specify the server (by default, it is localhost) and the database name (it must be called
              'reqs_prototype').
  . sql.user : User name used to connect to the database (it is root by default).
  . sql.pass : Password used to connect to the database (it is empty by default).
  . output.directory : Path where to export the web project.
- Run this ANT scripts
- It's ready !

Pointers & License notices :
----------------------------
For support, refer to: http://www.side-labs.org/forum
More information at http://www.side-labs.org and www.bluexml.com

Copyright (C) 2007-2009  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
