package com.filippochinni.inventoryapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val lightColorScheme = lightColorScheme(
	primary = Main_PrimaryLight,
	onPrimary = Main_OnPrimaryLight,
	primaryContainer = Main_PrimaryContainerLight,
	onPrimaryContainer = Main_OnPrimaryContainerLight,
	inversePrimary = Main_InversePrimaryLight,
	secondary = Main_SecondaryLight,
	onSecondary = Main_OnSecondaryLight,
	secondaryContainer = Main_SecondaryContainerLight,
	onSecondaryContainer = Main_OnSecondaryContainerLight,
	tertiary = Main_TertiaryLight,
	onTertiary = Main_OnTertiaryLight,
	tertiaryContainer = Main_TertiaryContainerLight,
	onTertiaryContainer = Main_OnTertiaryContainerLight,
	background = Main_BackgroundLight,
	onBackground = Main_OnBackgroundLight,
	surface = Main_SurfaceLight,
	onSurface = Main_OnSurfaceLight,
	surfaceVariant = Main_SurfaceVariantLight,
	onSurfaceVariant = Main_OnSurfaceVariantLight,
	surfaceTint = Main_SurfaceTintLight,
	inverseSurface = Main_InverseSurfaceLight,
	inverseOnSurface = Main_InverseOnSurfaceLight,
	error = Main_ErrorLight,
	onError = Main_OnErrorLight,
	errorContainer = Main_ErrorContainerLight,
	onErrorContainer = Main_OnErrorContainerLight,
	outline = Main_OutlineLight,
	outlineVariant = Main_OutlineVariantLight,
	scrim = Main_ScrimLight,
	surfaceBright = Main_SurfaceBrightLight,
	surfaceContainer = Main_SurfaceContainerLight,
	surfaceContainerHigh = Main_SurfaceContainerHighLight,
	surfaceContainerHighest = Main_SurfaceContainerHighestLight,
	surfaceContainerLow = Main_SurfaceContainerLowLight,
	surfaceContainerLowest = Main_SurfaceContainerLowestLight,
	surfaceDim = Main_SurfaceDimLight,
	primaryFixed = Main_PrimaryFixed,
	primaryFixedDim = Main_PrimaryFixedDim,
	onPrimaryFixed = Main_OnPrimaryFixed,
	onPrimaryFixedVariant = Main_OnPrimaryFixedVariant,
	secondaryFixed = Main_SecondaryFixed,
	secondaryFixedDim = Main_SecondaryFixedDim,
	onSecondaryFixed = Main_OnSecondaryFixed,
	onSecondaryFixedVariant = Main_OnSecondaryFixedVariant,
	tertiaryFixed = Main_TertiaryFixed,
	tertiaryFixedDim = Main_TertiaryFixedDim,
	onTertiaryFixed = Main_OnTertiaryFixed,
	onTertiaryFixedVariant = Main_OnTertiaryFixedVariant,
)

private val darkColorScheme = darkColorScheme(
	primary = Main_PrimaryDark,
	onPrimary = Main_OnPrimaryDark,
	primaryContainer = Main_PrimaryContainerDark,
	onPrimaryContainer = Main_OnPrimaryContainerDark,
	inversePrimary = Main_InversePrimaryDark,
	secondary = Main_SecondaryDark,
	onSecondary = Main_OnSecondaryDark,
	secondaryContainer = Main_SecondaryContainerDark,
	onSecondaryContainer = Main_OnSecondaryContainerDark,
	tertiary = Main_TertiaryDark,
	onTertiary = Main_OnTertiaryDark,
	tertiaryContainer = Main_TertiaryContainerDark,
	onTertiaryContainer = Main_OnTertiaryContainerDark,
	background = Main_BackgroundDark,
	onBackground = Main_OnBackgroundDark,
	surface = Main_SurfaceDark,
	onSurface = Main_OnSurfaceDark,
	surfaceVariant = Main_SurfaceVariantDark,
	onSurfaceVariant = Main_OnSurfaceVariantDark,
	surfaceTint = Main_SurfaceTintDark,
	inverseSurface = Main_InverseSurfaceDark,
	inverseOnSurface = Main_InverseOnSurfaceDark,
	error = Main_ErrorDark,
	onError = Main_OnErrorDark,
	errorContainer = Main_ErrorContainerDark,
	onErrorContainer = Main_OnErrorContainerDark,
	outline = Main_OutlineDark,
	outlineVariant = Main_OutlineVariantDark,
	scrim = Main_ScrimDark,
	surfaceBright = Main_SurfaceBrightDark,
	surfaceContainer = Main_SurfaceContainerDark,
	surfaceContainerHigh = Main_SurfaceContainerHighDark,
	surfaceContainerHighest = Main_SurfaceContainerHighestDark,
	surfaceContainerLow = Main_SurfaceContainerLowDark,
	surfaceContainerLowest = Main_SurfaceContainerLowestDark,
	surfaceDim = Main_SurfaceDimDark,
	primaryFixed = Main_PrimaryFixed,
	primaryFixedDim = Main_PrimaryFixedDim,
	onPrimaryFixed = Main_OnPrimaryFixed,
	onPrimaryFixedVariant = Main_OnPrimaryFixedVariant,
	secondaryFixed = Main_SecondaryFixed,
	secondaryFixedDim = Main_SecondaryFixedDim,
	onSecondaryFixed = Main_OnSecondaryFixed,
	onSecondaryFixedVariant = Main_OnSecondaryFixedVariant,
	tertiaryFixed = Main_TertiaryFixed,
	tertiaryFixedDim = Main_TertiaryFixedDim,
	onTertiaryFixed = Main_OnTertiaryFixed,
	onTertiaryFixedVariant = Main_OnTertiaryFixedVariant,
)

@Composable
fun InventoryAppTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = false,
	content: @Composable () -> Unit
) {
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}

		darkTheme -> darkColorScheme
		else -> lightColorScheme
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}
