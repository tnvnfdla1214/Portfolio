package com.example.portfolio.customView.view

enum class OverlayTutorialViewItemTooltipPosition {
    BOTTOM,
    TOP,
}

internal fun OverlayTutorialViewItemTooltipPosition.reverse(): OverlayTutorialViewItemTooltipPosition =
    when (this) {
        OverlayTutorialViewItemTooltipPosition.BOTTOM -> OverlayTutorialViewItemTooltipPosition.TOP
        OverlayTutorialViewItemTooltipPosition.TOP -> OverlayTutorialViewItemTooltipPosition.BOTTOM
    }

enum class TooltipPosition {
    BOTTOM,
    TOP,
    LEFT,
    RIGHT,
}

@JvmSynthetic
internal fun TooltipPosition.reverse(): TooltipPosition = when (this) {
    TooltipPosition.BOTTOM -> TooltipPosition.TOP
    TooltipPosition.TOP -> TooltipPosition.BOTTOM
    TooltipPosition.LEFT -> TooltipPosition.RIGHT
    TooltipPosition.RIGHT -> TooltipPosition.LEFT
}
