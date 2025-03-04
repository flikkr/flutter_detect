// Autogenerated from Pigeon (v24.2.1), do not edit directly.
// See also: https://pub.dev/packages/pigeon
@file:Suppress("UNCHECKED_CAST", "ArrayInDataClass")

package com.flutter.detector.flutter_detect

import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMethodCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  return if (exception is FlutterError) {
    listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/** Generated class from Pigeon that represents data sent in messages. */
data class FlutterApp (
  val packageName: String,
  val flutterLibPath: String,
  val appLibPath: String,
  val dartVersion: String,
  val zipEntryPath: String? = null,
  val label: String? = null,
  val appVersion: String? = null,
  val iconBytes: ByteArray? = null
)
 {
  companion object {
    fun fromList(pigeonVar_list: List<Any?>): FlutterApp {
      val packageName = pigeonVar_list[0] as String
      val flutterLibPath = pigeonVar_list[1] as String
      val appLibPath = pigeonVar_list[2] as String
      val dartVersion = pigeonVar_list[3] as String
      val zipEntryPath = pigeonVar_list[4] as String?
      val label = pigeonVar_list[5] as String?
      val appVersion = pigeonVar_list[6] as String?
      val iconBytes = pigeonVar_list[7] as ByteArray?
      return FlutterApp(packageName, flutterLibPath, appLibPath, dartVersion, zipEntryPath, label, appVersion, iconBytes)
    }
  }
  fun toList(): List<Any?> {
    return listOf(
      packageName,
      flutterLibPath,
      appLibPath,
      dartVersion,
      zipEntryPath,
      label,
      appVersion,
      iconBytes,
    )
  }
}
private open class DetectorHostApiPigeonCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          FlutterApp.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is FlutterApp -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated interface from Pigeon that represents a handler of messages from Flutter. */
interface DetectorHostApi {
  fun getApps(): List<FlutterApp>
  fun getPackages(appLibPath: String, zipEntryPath: String?): List<String>

  companion object {
    /** The codec used by DetectorHostApi. */
    val codec: MessageCodec<Any?> by lazy {
      DetectorHostApiPigeonCodec()
    }
    /** Sets up an instance of `DetectorHostApi` to handle messages through the `binaryMessenger`. */
    @JvmOverloads
    fun setUp(binaryMessenger: BinaryMessenger, api: DetectorHostApi?, messageChannelSuffix: String = "") {
      val separatedMessageChannelSuffix = if (messageChannelSuffix.isNotEmpty()) ".$messageChannelSuffix" else ""
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.flutter_detect.DetectorHostApi.getApps$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            val wrapped: List<Any?> = try {
              listOf(api.getApps())
            } catch (exception: Throwable) {
              wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.flutter_detect.DetectorHostApi.getPackages$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val appLibPathArg = args[0] as String
            val zipEntryPathArg = args[1] as String?
            val wrapped: List<Any?> = try {
              listOf(api.getPackages(appLibPathArg, zipEntryPathArg))
            } catch (exception: Throwable) {
              wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
