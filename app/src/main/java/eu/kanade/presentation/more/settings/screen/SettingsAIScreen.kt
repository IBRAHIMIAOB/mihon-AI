package eu.kanade.presentation.more.settings.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import tachiyomi.presentation.core.util.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import eu.kanade.presentation.more.settings.Preference
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.collections.immutable.PersistentList
import tachiyomi.domain.ai.service.AIPreferences
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.i18n.stringResource
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import tachiyomi.domain.ai.service.GoogleProvider
import tachiyomi.domain.ai.service.AIPromptBuilder

object SettingsAIScreen : SearchableSettings {

    @ReadOnlyComposable
    @Composable
    override fun getTitleRes() = MR.strings.pref_ai_coloring // Need to add string

    @Composable
    override fun getPreferences(): List<Preference> {
        val aiPreferences = remember { Injekt.get<AIPreferences>() }


        return listOf(
            getGeneralGroup(aiPreferences),
            getProviderGroup(aiPreferences)
        )
    }

    @Composable
    private fun getGeneralGroup(aiPreferences: AIPreferences): Preference.PreferenceGroup {
        return Preference.PreferenceGroup(
            title = stringResource(MR.strings.pref_ai_coloring),
            preferenceItems = persistentListOf(
                Preference.PreferenceItem.SwitchPreference(
                    preference = aiPreferences.aiColoringEnabled(),
                    title = stringResource(MR.strings.pref_ai_coloring),
                    subtitle = stringResource(MR.strings.pref_ai_coloring_summary)
                ),
                Preference.PreferenceItem.ListPreference(
                    preference = aiPreferences.aiProvider(),
                    entries = persistentMapOf(
                        "google" to "Google (Gemini)",
                        "openai" to "OpenAI",
                        "grok" to "Grok"
                    ),
                    title = stringResource(MR.strings.pref_ai_provider)
                )
            )
        )
    }

    @Composable
    private fun getProviderGroup(aiPreferences: AIPreferences): Preference.PreferenceGroup {
        val provider by aiPreferences.aiProvider().collectAsState()
        val textAction by aiPreferences.aiTextAction().collectAsState()

        val models = when (provider) {
            "google", "nanobanana" -> GoogleProvider.SUPPORTED_MODELS.associateWith { it }
            "openai" -> mapOf("gpt-4o" to "GPT-4o", "gpt-4-turbo" to "GPT-4 Turbo")
            "grok" -> mapOf("grok-beta" to "Grok Beta")
            else -> emptyMap()
        }.toPersistentMap()

        val styles = AIPromptBuilder.STYLES.keys.associateWith { it }.toPersistentMap()

        val baseItems: PersistentList<Preference.PreferenceItem<out Any, out Any>> = persistentListOf(
                Preference.PreferenceItem.ListPreference(
                    preference = aiPreferences.aiModel(),
                    entries = models,
                    title = stringResource(MR.strings.pref_ai_model),
                ),
                Preference.PreferenceItem.ListPreference(
                    preference = aiPreferences.aiStyle(),
                    entries = styles,
                    title = stringResource(MR.strings.pref_ai_style),
                ),
                Preference.PreferenceItem.ListPreference(
                    preference = aiPreferences.aiTextAction(),
                    entries = persistentMapOf(
                        "none" to "None (Keep Text)",
                        "translate" to "Translate",
                        "remove_text" to "Remove Text (Keep Bubbles)",
                        "remove_bubbles" to "Remove Bubbles",
                        "whiten" to "Whiten Bubbles"
                    ),
                    title = stringResource(MR.strings.pref_ai_text_action),
                ),
            )

        val items = baseItems.builder()

        if (textAction == "translate") {
            items.add(
                Preference.PreferenceItem.EditTextPreference(
                    preference = aiPreferences.aiTargetLanguage(),
                    title = stringResource(MR.strings.pref_ai_target_language),
                    subtitle = stringResource(MR.strings.pref_ai_target_language_summary),
                )
            )
        }
        items.add(
            Preference.PreferenceItem.EditTextPreference(
                preference = aiPreferences.aiApiKey(),
                title = stringResource(MR.strings.pref_ai_api_key),
                subtitle = stringResource(MR.strings.pref_ai_api_key_summary)
            )
        )
        items.add(
            Preference.PreferenceItem.EditTextPreference(
                preference = aiPreferences.aiPrompt(),
                title = stringResource(MR.strings.pref_ai_additional_instructions),
                subtitle = stringResource(MR.strings.pref_ai_additional_instructions_summary)
            )
        )

        return Preference.PreferenceGroup(
            title = when (provider) {
                "google", "nanobanana" -> "Google (Gemini) Settings"
                "openai" -> "OpenAI Settings"
                "grok" -> "Grok Settings"
                else -> "AI Provider Settings"
            },
            preferenceItems = items.build()
        )
    }
}
