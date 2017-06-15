package cool.oneshot.rxjavaapp.annotations;

/**
 * Marks classes that can be (de)serialized (i.e. through Gson JSON parsing library) for backend API
 * contracts (request and/or response). This is useful for keeping these model classes fully intact
 * when ProGuard obfuscation is enabled.
 */
public @interface ApiSerializable {
}