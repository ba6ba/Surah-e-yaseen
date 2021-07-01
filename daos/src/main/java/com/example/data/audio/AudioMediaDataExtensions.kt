package com.example.data.audio

val AudioMediaData.toServiceMetaData: ServiceMetaData
    get() = ServiceMetaData(
        title = title,
        id = data?.id ?: throwInvalidAudioMediaException("id"),
        url = metaData?.url ?: throwInvalidAudioMediaException("Url"),
        audioId = data?.audioId ?: throwInvalidAudioMediaException("Audio id"),
        authorName = authorData?.name ?: throwInvalidAudioMediaException("Author name"),
        audioDuration = metaData?.audioDuration ?: throwInvalidAudioMediaException("Duration"),
        imageRes = imageMetaData?.imageDrawableRes ?: throwInvalidAudioMediaException("Image drawable res")
    )

val AudioMediaData.MediaMetaData.isValid
    get() = mediaId.isNotEmpty() and mediaUri.isNotEmpty()

private fun throwInvalidAudioMediaException(happenedOn: String): Nothing = throw InvalidAudioMediaDataException.generate(happenedOn)