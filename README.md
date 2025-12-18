<div align="center">

<a href="https://mihon.app">
    <img src="./.github/assets/logo.png" alt="Mihon logo" title="Mihon logo" width="80"/>
</a>

# Mihon AI Fork

### Full-featured reader with AI Features
This is a customized fork of [Mihon](https://github.com/mihonapp/mihon) that adds the ability to Color manga panels and more using AI.

[![Discord server](https://img.shields.io/discord/1195734228319617024.svg?label=&labelColor=6A7EC2&color=7389D8&logo=discord&logoColor=FFFFFF)](https://discord.gg/mihon)
<!-- [![GitHub downloads](https://img.shields.io/github/downloads/mihonapp/mihon/total?label=downloads&labelColor=27303D&color=0D1117&logo=github&logoColor=FFFFFF&style=flat)](https://mihon.app/download) -->

[![CI](https://img.shields.io/github/actions/workflow/status/mihonapp/mihon/build.yml?labelColor=27303D)](https://github.com/mihonapp/mihon/actions/workflows/build_push.yml)
[![License: Apache-2.0](https://img.shields.io/github/license/mihonapp/mihon?labelColor=27303D&color=0877d2)](/LICENSE)
[![Translation status](https://img.shields.io/weblate/progress/mihon?labelColor=27303D&color=946300)](https://hosted.weblate.org/engage/mihon/)

## Download

[Download](https://github.com/IBRAHIMIAOB/mihon-AI-coloring/releases/tag/Dev)

<!--
[![Mihon Stable](https://img.shields.io/github/release/mihonapp/mihon.svg?maxAge=3600&label=Stable&labelColor=06599d&color=043b69)](https://mihon.app/download)
[![Mihon Beta](https://img.shields.io/github/v/release/mihonapp/mihon-preview.svg?maxAge=3600&label=Beta&labelColor=2c2c47&color=1c1c39)](https://mihon.app/download)
-->

*Requires Android 8.0 or higher.*

</div>

## AI Coloring & Features

This fork enables AI-powered coloring of manga chapters and other enhancements.

### Changed Files
The core changes for these features can be found in:
- `domain/src/main/java/tachiyomi/domain/ai/service`
- `app/src/main/java/eu/kanade/presentation/more/settings/screen/SettingsAIScreen.kt`
- `app/src/main/java/eu/kanade/presentation/reader/ColorCanvasDialog.kt`
- and more...

### How to Use (Manual Testing Guide)

To use the AI coloring features, follow these steps:

1.  **Get an API Key**: Obtain an API key from [OpenRouter](https://openrouter.ai/settings/keys).
2.  **Add Credits**: Ensure you have added credits to your OpenRouter account.
3.  **Choose a Model**: Select a supported model. See the list of models here: [OpenRouter Models](https://openrouter.ai/models?fmt=cards&input_modalities=image%2Ctext&output_modalities=image).
4.  **Configure in App**:
    - Go to **Settings > AI Coloring**.
    - Enter your **API Key**.
    - Enable **AI Coloring**.
    - Enter the **Model ID** (e.g., `google/gemini-2.0-flash-exp`).
    - Select your **Style** and **Translation** options (Note: Translation may not work effectively with weaker models).
5.  **Download & Color**:
    - Download a chapter.
    - The coloring process will occur automatically during the download.

### Future Features
- **AI Coloring Guidance**: Allow users to draw/scribble guides so the AI colors based on user preferences.
- **Reference Style**: Capability for users to provide a reference image for the AI to copy the coloring style.
- **Motion Images**: Convert manga panels into GIF motion images using OpenRouter's image-to-video capabilities.

### Known Issues
- **Weak Image Gen Models**: Issues are often related to using weak image generation models. It is recommended to use high-quality models (e.g., successors to Nanobabab).
- **User Coloring UI**: The manual coloring interface (`ColorCanvasDialog.kt`) is implemented simply and may not fully reflect in the UI immediately.
- **Storage/Caching**: Currently, coloring happens upon download. A better method is needed to color images per panel on-demand and save them in cache/storage more efficiently.

---

## Original Features

<div align="left">

* Local reading of content.
* A configurable reader with multiple viewers, reading directions and other settings.
* Tracker support: [MyAnimeList](https://myanimelist.net/), [AniList](https://anilist.co/), [Kitsu](https://kitsu.app/), [MangaUpdates](https://mangaupdates.com), [Shikimori](https://shikimori.one), and [Bangumi](https://bgm.tv/) support.
* Categories to organize your library.
* Light and dark themes.
* Schedule updating your library for new chapters.
* Create backups locally to read offline or to your desired cloud service.
* Plus much more...

</div>

## Contributing

[Code of conduct](./CODE_OF_CONDUCT.md) · [Contributing guide](./CONTRIBUTING.md)

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Before reporting a new issue, take a look at the [FAQ](https://mihon.app/docs/faq/general), the [changelog](https://mihon.app/changelogs/) and the already opened [issues](https://github.com/mihonapp/mihon/issues); if you got any questions, join our [Discord server](https://discord.gg/mihon).


### Repositories

[![mihonapp/website - GitHub](https://github-readme-stats.vercel.app/api/pin/?username=mihonapp&repo=website&bg_color=161B22&text_color=c9d1d9&title_color=0877d2&icon_color=0877d2&border_radius=8&hide_border=true&description_lines_count=2)](https://github.com/mihonapp/website/)
[![mihonapp/bitmap.kt - GitHub](https://github-readme-stats.vercel.app/api/pin/?username=mihonapp&repo=bitmap.kt&bg_color=161B22&text_color=c9d1d9&title_color=0877d2&icon_color=0877d2&border_radius=8&hide_border=true&description_lines_count=2)](https://github.com/mihonapp/bitmap.kt/)

### Credits

Thank you to all the people who have contributed!

<a href="https://github.com/mihonapp/mihon/graphs/contributors">
    <img src="https://contrib.rocks/image?repo=mihonapp/mihon" alt="Mihon app contributors" title="Mihon app contributors" width="800"/>
</a>

### Disclaimer

The developer(s) of this application does not have any affiliation with the content providers available, and this application hosts zero content.

### License

<pre>
Copyright © 2015 Javier Tomás
Copyright © 2024 Mihon Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>

</div>
