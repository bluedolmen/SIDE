/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.alfresco.repo.content.cleanup;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TrashcanCleanerJob implements Job
{

   public void execute(JobExecutionContext context) throws JobExecutionException
   {
      JobDataMap jobData = context.getJobDetail().getJobDataMap();
      // extract the content cleaner to use
      Object trashcanCleanerObj = jobData.get("trashcanCleaner");
      if (trashcanCleanerObj == null || !(trashcanCleanerObj instanceof TrashcanCleaner))
      {
          throw new AlfrescoRuntimeException(
                  "TrashcanCleanerJob data must contain valid 'trashcanCleaner' reference");
      }
      final TrashcanCleaner trashcanCleaner = (TrashcanCleaner) trashcanCleanerObj;

      AuthenticationUtil.runAs(new AuthenticationUtil.RunAsWork<Object>()
      {
         /* (non-Javadoc)
          * @see org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork#doWork()
          */
         public Object doWork() throws Exception
         {
            trashcanCleaner.execute();
            return null;
         }
      }, AuthenticationUtil.getSystemUserName());
   }

}
