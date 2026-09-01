package com.filippochinni.inventoryapp.ui.screen.settingsGroup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.filippochinni.inventoryapp.R


@Composable
fun SettingsAboutScreen() {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxWidth()
			.padding(
				horizontal = dimensionResource(R.dimen.screen_border_padding_hor),
				vertical = dimensionResource(R.dimen.screen_border_padding_ver)
			)
	) {
		Text(
			text = stringResource(R.string.app_name),
			style = MaterialTheme.typography.displayLarge,
			textAlign = TextAlign.Center,
			color = MaterialTheme.colorScheme.primary,
			modifier = Modifier
		)
		Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))
		Image(
			painter = painterResource(R.drawable.inventoryapp_icon),
			contentDescription = null,
			modifier = Modifier
		)
		Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_extra_large)))
		Text(
			text = stringResource(R.string.app_author),
			style = MaterialTheme.typography.headlineMedium,
			textAlign = TextAlign.Center,
			modifier = Modifier
		)
		Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))
		Text(
			text = stringResource(R.string.app_version),
			style = MaterialTheme.typography.titleMedium,
			textAlign = TextAlign.Center,
			modifier = Modifier
		)

	}
}
