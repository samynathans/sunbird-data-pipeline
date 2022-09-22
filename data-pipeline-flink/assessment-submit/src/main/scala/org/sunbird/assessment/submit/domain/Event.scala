package org.sunbird.assessment.submit.domain

import org.sunbird.dp.core.domain.{Events, EventsPath}

import java.util

class Event(eventMap: util.Map[String, Any]) extends Events(eventMap) {
  private val jobName = "assessmentFeatureJob"

  def contentId: String = {
    telemetry.read[String]("contentId").get
  }

  def courseId: String = {
    telemetry.read[String]("courseId").get
  }

  def batchId: String = {
    telemetry.read[String]("batchId").get
  }

  def userId: String = {
    telemetry.read[String]("userId").get
  }

  def assessmentId: String = {
    telemetry.read[String]("assessmentId").get
  }

  def markFailed(errorMsg: String): Unit = {
    telemetry.addFieldIfAbsent(EventsPath.FLAGS_PATH, new util.HashMap[String, Boolean])
    telemetry.addFieldIfAbsent("metadata", new util.HashMap[String, AnyRef])
    telemetry.add("metadata.validation_error", errorMsg)
    telemetry.add("metadata.src", jobName)
  }

}
