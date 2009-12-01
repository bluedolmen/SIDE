/***
   RWM : Requirements Web Modeler
   Copyright (C) 2008 (BlueXML / IRCCyN)

   This program is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public License
   as published by the Free Software Foundation; either version 2
   of the License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
***/

draw2d.EndFigure=function()
{
  this.target = null;
  this.min = "0";
  this.max = "1";
}

draw2d.EndFigure.prototype.getMin = function() {
  return this.min;
}

draw2d.EndFigure.prototype.getMax = function() {
  if (this.max == '*')
    return '-1';
   else
    return this.max;
}

draw2d.EndFigure.prototype.setMin = function(/*:String*/ value) {
  this.min = value;
}

draw2d.EndFigure.prototype.setMax = function(/*:String*/ value) {
  this.max = value;
}

draw2d.EndFigure.prototype.toXMI=function() {
  if (this.target)
    return '<ends object="'+this.target.getId()+'" minCardinality="'+this.getMin()+'" maxCardinality="'+this.getMax()+'"/>\n';
  else
    return '<ends minCardinality="'+this.getMin()+'" maxCardinality="'+this.getMax()+'"/>\n';
}
