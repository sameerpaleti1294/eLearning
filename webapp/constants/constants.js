sap.ui.define([
    'jquery.sap.global'
], function (jQuery) {
    'use strict';

    var constants = {
        authentication_url :"http://localhost:8081/api/authentication",
        myCourses_url: "http://localhost:8081/api/user/getCourses/{userId}",
        coursesSchedule_url: "http://localhost:8081/api/schedule/forCourseId/{courseId}",
        mySchedule_url: "http://localhost:8081/api/user/getSchedules/{userId}/{date}",
        getFile_url: "http://localhost:8081/api/files/download/{scheduleId}",
        downloadFile_url: "http://localhost:8081/api/files/download/{scheduleId}"

    };
    return constants;
});